package sptech.faztudo.comLOCAL.users.controllers;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;
import sptech.faztudo.comLOCAL.email.Email;
import sptech.faztudo.comLOCAL.email.EmailService;
import sptech.faztudo.comLOCAL.users.domain.contractor.Contractor;
import sptech.faztudo.comLOCAL.users.domain.contractor.RegisterContractorDTO;
import sptech.faztudo.comLOCAL.users.domain.serviceProvider.RegisterServiceProviderDTO;
import sptech.faztudo.comLOCAL.users.domain.serviceProvider.ServiceProvider;
import sptech.faztudo.comLOCAL.users.domain.users.*;
import sptech.faztudo.comLOCAL.users.repositorys.ForgotPasswordRepository;
import sptech.faztudo.comLOCAL.users.security.TokeService;
import sptech.faztudo.comLOCAL.users.security.tokenForRegister.ConfirmationToken;
import sptech.faztudo.comLOCAL.users.security.tokenForRegister.ConfirmationTokenService;
import sptech.faztudo.comLOCAL.users.repositorys.UserRepository;
import sptech.faztudo.comLOCAL.users.repositorys.serviceProviderRepository;
import sptech.faztudo.comLOCAL.users.repositorys.contractorRepository;
import sptech.faztudo.comLOCAL.users.services.AuthorizationService;
import sptech.faztudo.comLOCAL.users.services.UserPasswordService;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository repository;

    @Autowired
    private ForgotPasswordRepository forgotPasswordRepository;

    @Autowired
    private serviceProviderRepository serviceProviderRepository;

    @Autowired
    private contractorRepository contractorRepository;

    @Autowired
    private TokeService tokeService;

    @Autowired
    private ConfirmationTokenService confirmationTokenService;

    @Autowired
    private AuthorizationService authorizationService;

    @Autowired
    private UserPasswordService userPasswordService;

    @Autowired
    private EmailService emailService;





    @PostMapping("/login")
    @Operation(summary = "Login", description = "Login para Users.", tags = "USER")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){

        var userNamePassWord = new UsernamePasswordAuthenticationToken(data.email(), data.senha());

        var auth = this.authenticationManager.authenticate(userNamePassWord);

        var login = auth.getPrincipal();
        var token = tokeService.generateToken((User) auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDTO(token, login));
    }


    @PostMapping("/register")
    @Operation(summary = "Register", description = "Register para ADMIN.", tags = "USER")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data, UriComponentsBuilder uriComponentsBuilder){

        if(!authorizationService.isUserValid(data) && this.repository.findByEmail(data.email()) != null) return ResponseEntity.status(400).build();
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.senha());
        LocalDateTime cad = LocalDateTime.now();
        User newUser = new User(data.name(),data.lastName(),data.cpf(),data.dt_nascimento(),data.cep(),data.logradouro(),data.state(),data.city()
                ,data.phone(),data.email(),encryptedPassword,cad,data.descricao(),data.image_profile(), data.role());
        this.repository.save(newUser);
        var uri = uriComponentsBuilder.path("/users/{id}").buildAndExpand(newUser.getId()).toUri();
        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(120),
                newUser
        );
        String link = "http://localhost:8080/auth/register/confirm?token=" + token;
        confirmationTokenService.saveConfirmationToken(confirmationToken);
        return ResponseEntity.created(uri).body(confirmationToken);
    }


    @PostMapping("/register-service-provider")
    @Operation(summary = "Register", description = "Register para service provider.", tags = "USER")
    public ResponseEntity registerServiceProvider(@RequestBody @Valid RegisterServiceProviderDTO dataServiceProvider, UriComponentsBuilder uriComponentsBuilder){

            if(!authorizationService.isServiceProviderValid(dataServiceProvider) && this.serviceProviderRepository.findByEmail(dataServiceProvider.email()) != null) return ResponseEntity.status(400).build();
            String encryptedPassword = new BCryptPasswordEncoder().encode(dataServiceProvider.senha());
            LocalDateTime cad = LocalDateTime.now();
          ServiceProvider newServiceProvider = new ServiceProvider(dataServiceProvider.name(), dataServiceProvider.lastName(),
                    dataServiceProvider.cpf(), dataServiceProvider.dt_nascimento(), dataServiceProvider.cep(),
                    dataServiceProvider.logradouro(), dataServiceProvider.state(),
                    dataServiceProvider.city(), dataServiceProvider.phone(), dataServiceProvider.email(),
                    encryptedPassword,cad,dataServiceProvider.descricao(),dataServiceProvider.category(), dataServiceProvider.image_profile(), dataServiceProvider.role());
            var uri = uriComponentsBuilder.path("/users/{id}").buildAndExpand(newServiceProvider.getId()).toUri();
            this.serviceProviderRepository.save(newServiceProvider);
            return ResponseEntity.created(uri).body(newServiceProvider);
    }

    @PostMapping("/register-contractor")
    @Operation(summary = "Register", description = "Register para contractor.", tags = "USER")
    public ResponseEntity registerContractor(@RequestBody @Valid RegisterContractorDTO dataContractor, UriComponentsBuilder uriComponentsBuilder){

            if(!authorizationService.isServiceProviderValid(dataContractor) && this.contractorRepository.findByEmail(dataContractor.email()) != null) return ResponseEntity.status(400).build();
            String encryptedPassword = new BCryptPasswordEncoder().encode(dataContractor.senha());
            LocalDateTime cad = LocalDateTime.now();
            Contractor newContractor = new Contractor(dataContractor.name(), dataContractor.lastName(),
                    dataContractor.cpf(),dataContractor.dt_nascimento(), dataContractor.cep(),dataContractor.logradouro() ,
                    dataContractor.state(), dataContractor.city(), dataContractor.phone(), dataContractor.email(), encryptedPassword,cad,
                    dataContractor.descricao(), dataContractor.image_profile(),
                    dataContractor.proUser(),dataContractor.role());
            var uri = uriComponentsBuilder.path("/users/{id}").buildAndExpand(newContractor.getId()).toUri();
            this.contractorRepository.save(newContractor);
            return ResponseEntity.created(uri).body(newContractor);
    }

    @PostMapping("/public/forgot-password")
    @Operation(summary = "Password", description = "Recuperação senha", tags = "USER")
    public void forgotPassword(@RequestBody @Valid ForgotPassword forgotPassword) {
        Optional<User> optionalUser = Optional.ofNullable(forgotPasswordRepository.findByEmail(forgotPassword.getEmail()));
        optionalUser.ifPresent(user -> {
            String token = userPasswordService.generateToken(user);
            String conteudo = "http://localhost:5173/redefinir-senha/" + token;
            Email email = new Email(forgotPassword.getEmail(), "Recuperação de senha", conteudo);
            emailService.sendEmail(email);
        });
    }

    @PostMapping("/public/change-password/{token}")
    @Operation(summary = "Password", description = "Recuperação senha", tags = "USER")
    public void changePassword(
            @RequestBody @Valid ForgotPasswordWithToken forgotPasswordWithToken,
            @PathVariable String token) {
        try {
            userPasswordService.forgotPassword(forgotPasswordWithToken.getPassword(), token);
        }catch (Exception e) {
            log.error("Erro ao alterar a senha usando token", e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/confirm")
    @Operation(summary = "Token", description = "Confirmar token", tags = "USER")
    public String confirm(@RequestParam("token") String token) {
        return authorizationService.confirmToken(token);
    }






}

