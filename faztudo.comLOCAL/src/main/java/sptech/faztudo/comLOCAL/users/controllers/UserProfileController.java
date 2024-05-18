package sptech.faztudo.comLOCAL.users.controllers;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sptech.faztudo.comLOCAL.post.domainPost.upload.Image;
import sptech.faztudo.comLOCAL.users.domain.contractor.Contractor;
import sptech.faztudo.comLOCAL.users.domain.contractor.UpdateContractorDTO;
import sptech.faztudo.comLOCAL.users.domain.contractor.UpdateProUser;
import sptech.faztudo.comLOCAL.users.domain.serviceProvider.ServiceProvider;
import sptech.faztudo.comLOCAL.users.domain.serviceProvider.UpdateServiceProviderDTO;
import sptech.faztudo.comLOCAL.users.domain.users.UpdateDescriptionDTO;
import sptech.faztudo.comLOCAL.users.domain.users.UpdateImageProfile;
import sptech.faztudo.comLOCAL.users.domain.users.UpdateUserPasswordDTO;
import sptech.faztudo.comLOCAL.users.domain.users.User;
import sptech.faztudo.comLOCAL.users.repositorys.UserRepository;
import sptech.faztudo.comLOCAL.users.repositorys.contractorRepository;
import sptech.faztudo.comLOCAL.users.repositorys.serviceProviderRepository;

import java.io.IOException;
import java.util.Optional;

@Controller
@RequestMapping("profile")
public class UserProfileController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository repository;


    @Autowired
    private sptech.faztudo.comLOCAL.users.repositorys.contractorRepository contractorRepository;

    @Autowired
    private sptech.faztudo.comLOCAL.users.repositorys.serviceProviderRepository serviceProviderRepository;


    @PatchMapping("/update-contractor/{id}")
    @Operation(summary = "Update", description = "Atualiza dados de contratantes.", tags = "PROFILE")
    public ResponseEntity<Contractor> updateContractor(
            @PathVariable int id,
            @RequestBody @Valid UpdateContractorDTO updateContractorDTO
            ) {
        if (contractorRepository.existsById(id)){
            Contractor contractor = contractorRepository.findById(id);
            contractor.setId(id);
            contractor.setCep(updateContractorDTO.cep());
            contractor.setLogradouro(updateContractorDTO.logradouro());
            contractor.setState(updateContractorDTO.state());
            contractor.setCity(updateContractorDTO.city());
            contractor.setPhone(updateContractorDTO.phone());
            return ResponseEntity.status(200).body(contractorRepository.save(contractor));
        }

        return ResponseEntity.status(404).build();
    }

    @PatchMapping("/update-contractor-prouser/{id}")
    @Operation(summary = "Update Description", description = "Atualiza a descrição de perfil para todos os usuários.", tags = "PROFILE")
    public ResponseEntity<Contractor> updateProUser(
            @RequestBody UpdateProUser partialUpdate,
            @PathVariable int id
    ) {
        if (contractorRepository.existsById(id)){
            Contractor contractor = contractorRepository.findById(id);
            contractor.setId(id);
            contractor.setProUser(partialUpdate.proUser());
            return ResponseEntity.status(200).body(contractorRepository.save(contractor));
        }

        return ResponseEntity.status(404).build();

    }

    @PatchMapping("/update-service-provider/{id}")
    @Operation(summary = "Update", description = "Atualiza dados de prestadores.", tags = "PROFILE")
    public ResponseEntity<ServiceProvider> updateServiceProvider(
            @PathVariable int id,
            @RequestBody @Valid UpdateServiceProviderDTO serviceProvider
    ) {
        if (serviceProviderRepository.existsById(id)){
            ServiceProvider servicePro = serviceProviderRepository.findById(id);
            servicePro.setId(id);
            servicePro.setCep(serviceProvider.cep());
            servicePro.setLogradouro(serviceProvider.logradouro());
            servicePro.setState(serviceProvider.state());
            servicePro.setCity(serviceProvider.city());
            servicePro.setPhone(serviceProvider.phone());
            servicePro.setCategory(serviceProvider.category());
            return ResponseEntity.status(200).body(serviceProviderRepository.save(servicePro));
        }

        return ResponseEntity.status(404).build();
    }

    @PatchMapping("/update-password/{id}")
    @Operation(summary = "Update Password", description = "Atualiza senha para todos os usuários.", tags = "PROFILE")
    public ResponseEntity<User> updatePassword(
            @RequestBody UpdateUserPasswordDTO partialUpdate,
            @PathVariable int id
    ) {
        User newPassword =  repository.findById(id);
        String senha = passwordEncoder.encode(partialUpdate.senha());
        newPassword.setSenha(senha);

        return ResponseEntity.status(200).body(repository.save(newPassword));

    }


    @PatchMapping("/update-description/{id}")
    @Operation(summary = "Update Description", description = "Atualiza a descrição de perfil para todos os usuários.", tags = "PROFILE")
    public ResponseEntity<User> updateDescription(
            @RequestBody UpdateDescriptionDTO partialUpdate,
            @PathVariable int id
    ) {
        User newDescriprion =  repository.findById(id);
        newDescriprion.setDescricao(partialUpdate.descricao());

        return ResponseEntity.status(200).body(repository.save(newDescriprion));

    }


    @PatchMapping("/update-image-profile/{id}")
    @Operation(summary = "Update Image profile", description = "Atualiza a imagem de perfil para todos os usuários.", tags = "PROFILE")
    public ResponseEntity updateImageProfile(
            @RequestParam("file") MultipartFile file,
            @PathVariable int id
    ) throws IOException {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Arquivo não enviado");
        }

        User user = repository.findById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        user.setImage_profile(file.getBytes());
        repository.save(user);

        return ResponseEntity.ok(user);


    }
}
