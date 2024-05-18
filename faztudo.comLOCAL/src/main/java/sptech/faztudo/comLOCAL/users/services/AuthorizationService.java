package sptech.faztudo.comLOCAL.users.services;

import io.micrometer.common.util.StringUtils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sptech.faztudo.comLOCAL.users.domain.contractor.RegisterContractorDTO;
import sptech.faztudo.comLOCAL.users.domain.users.RegisterDTO;
import sptech.faztudo.comLOCAL.users.domain.serviceProvider.RegisterServiceProviderDTO;
import sptech.faztudo.comLOCAL.users.security.tokenForRegister.ConfirmationToken;
import sptech.faztudo.comLOCAL.users.security.tokenForRegister.ConfirmationTokenService;
import sptech.faztudo.comLOCAL.users.repositorys.UserRepository;

import java.time.LocalDateTime;

@Service
public class AuthorizationService implements UserDetailsService {

    @Autowired
    UserRepository repository;

    @Autowired
    private ConfirmationTokenService confirmationTokenService;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByEmail(username);
    }

    @Transactional
    public String confirmToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenService
                .getToken(token)
                .orElseThrow(() ->
                        new IllegalStateException("token not found"));

        if (confirmationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("email already confirmed");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiresAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("token expired");
        }

        confirmationTokenService.setConfirmedAt(token);
        return "confirmed";
    }

    public boolean isUserValid(RegisterDTO data) {
        return isValidName(data.name()) &&
                isValidLastName(data.lastName()) &&
                isValidCPF(data.cpf()) &&
                isValidEmail(data.email()) &&
                isValidPhone(data.phone()) &&
                isValidPassword(data.senha());
    }
    public boolean isServiceProviderValid(RegisterServiceProviderDTO dataServiceProvider) {
        return isValidName(dataServiceProvider.name()) &&
                isValidLastName(dataServiceProvider.lastName()) &&
                isValidCPF(dataServiceProvider.cpf()) &&
                isValidEmail(dataServiceProvider.email()) &&
                isValidPhone(dataServiceProvider.phone()) &&
                isValidPassword(dataServiceProvider.senha());
    }

    public boolean isServiceProviderValid(RegisterContractorDTO dataContractor) {
        return isValidName(dataContractor.name()) &&
                isValidLastName(dataContractor.lastName()) &&
                isValidCPF(dataContractor.cpf()) &&
                isValidEmail(dataContractor.email()) &&
                isValidPhone(dataContractor.phone()) &&
                isValidPassword(dataContractor.senha());
    }

    private boolean isValidName(String name) {
        return !StringUtils.isEmpty(name) && name.length() >= 5 && name.length() <= 50;
    }

    private boolean isValidLastName(String lastName) {
        return !StringUtils.isEmpty(lastName) && lastName.length() >= 5 && lastName.length() <= 150;
    }

    private boolean isValidCPF(String cpf) {
        return !StringUtils.isEmpty(cpf) && cpf.length() == 11;
    }

    private boolean isValidEmail(String email) {
        return !StringUtils.isEmpty(email) ;
    }

    private boolean isValidPhone(String phone) {
        return !StringUtils.isEmpty(phone) && phone.length() >= 8;
    }

    private boolean isValidPassword(String password) {
        return !StringUtils.isEmpty(password) && password.length() >= 8;
    }

//    public int enableAppUser(String email) {
//        return repository.enableAppUser(email);
//    }


}
