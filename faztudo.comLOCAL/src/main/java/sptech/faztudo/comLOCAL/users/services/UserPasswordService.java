package sptech.faztudo.comLOCAL.users.services;


import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.core.token.KeyBasedPersistenceTokenService;
import org.springframework.security.core.token.SecureRandomFactoryBean;
import org.springframework.security.core.token.Token;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sptech.faztudo.comLOCAL.users.domain.users.PasswordTokenPublicData;
import sptech.faztudo.comLOCAL.users.domain.users.User;
import sptech.faztudo.comLOCAL.users.repositorys.ForgotPasswordRepository;
import sptech.faztudo.comLOCAL.users.repositorys.UserRepository;

import java.time.Duration;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class UserPasswordService {

    private final UserRepository userRepository;
    private final ForgotPasswordRepository forgotPasswordRepository;
    private final PasswordEncoder passwordEncoder;

    @SneakyThrows
    public String generateToken(User user){
        KeyBasedPersistenceTokenService tokenService = getInstaceFor(user);
        Token token = tokenService.allocateToken(user.getEmail());

        return token.getKey();
    }

    @SneakyThrows
    public void forgotPassword(String newPassword, String rawToken){
        PasswordTokenPublicData publicData = readPublicData(rawToken);
        if (isExpired(publicData)){
            throw new RuntimeException("Token expirado");
        }

        User user = forgotPasswordRepository.findByEmail(publicData.getEmail());

        KeyBasedPersistenceTokenService tokenService = this.getInstaceFor(user);
        tokenService.verifyToken(rawToken);

        user.setSenha(this.passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }

    private boolean isExpired(PasswordTokenPublicData publicData) {
        Instant createdAt = new Date(publicData.getCreateAtTimestamp()).toInstant();
        Instant now = new Date().toInstant();
        return createdAt.plus(Duration.ofMinutes(5)).isBefore(now);
    }

    private KeyBasedPersistenceTokenService getInstaceFor(User user) throws Exception {
        KeyBasedPersistenceTokenService tokenService = new KeyBasedPersistenceTokenService();
        tokenService.setServerSecret(user.getPassword());
        tokenService.setServerInteger(16);
        tokenService.setSecureRandom(new SecureRandomFactoryBean().getObject());
        return tokenService;
    }

    public PasswordTokenPublicData readPublicData(String rawToken){
        byte[] bytes = Base64.getDecoder().decode(rawToken);
        String rawTokenDecoded = new String(bytes);

        System.out.println(rawTokenDecoded);

        String[] tokenParts = rawTokenDecoded.split(":");

        Long timestamp = Long.parseLong(tokenParts[0]);
        String email = tokenParts[2];
        return new PasswordTokenPublicData(email,timestamp);
    }
}
