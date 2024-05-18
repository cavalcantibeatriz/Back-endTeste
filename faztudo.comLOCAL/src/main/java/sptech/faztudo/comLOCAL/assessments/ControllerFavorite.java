package sptech.faztudo.comLOCAL.assessments;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sptech.faztudo.comLOCAL.post.repositoryPost.ContractorPostRepository;
import sptech.faztudo.comLOCAL.users.domain.contractor.Contractor;
import sptech.faztudo.comLOCAL.users.domain.serviceProvider.ServiceProvider;
import sptech.faztudo.comLOCAL.users.domain.users.User;
import sptech.faztudo.comLOCAL.users.repositorys.contractorRepository;
import sptech.faztudo.comLOCAL.users.repositorys.serviceProviderRepository;
import sptech.faztudo.comLOCAL.users.repositorys.UserRepository;

import java.util.List;

@RestController
@RequestMapping("/favorite")
public class ControllerFavorite {

    @Autowired
    private RepositoryFavorite favoriteRepository;

    @Autowired
    private contractorRepository contractorRepository;

    @Autowired
    private serviceProviderRepository serviceProviderRepository;

    @Autowired
    private UserRepository userRepository;


    @PostMapping("/{id_contractor}/{id_provider}")
    @Operation(summary = "Add Favorite by Id", description = "Adiciona um favorito por id", tags = "PROFILE")
    public ResponseEntity<?> addFavorite (@PathVariable int id_contractor ,@PathVariable int id_provider){
        try {
            Boolean serviceProvider = serviceProviderRepository.existsById(id_provider);
            Boolean contractor = contractorRepository.existsById(id_contractor);
           /* long idApagado = favoriteRepository.existsByFk(id_contractor,id_provider);*/
            if(!serviceProvider && !contractor){
                return ResponseEntity.status(404).build();}
            else {
            Favorite favorite = new Favorite(id_contractor,id_provider);
            favoriteRepository.save(favorite);}

        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.status(200).build();
    }

    @DeleteMapping("/{id_contractor}/{id_provider}")
    @Operation(summary = "Remove Favorite by Id", description = "Remove um favorito por id", tags = "PROFILE")
    public ResponseEntity<?> removeFavorite (@PathVariable int id_contractor ,@PathVariable int id_provider){

        long idApagado = favoriteRepository.existsByFk(id_contractor,id_provider);
        if(idApagado > 0 ){
            favoriteRepository.deleteById(idApagado);
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(404).build();
    }

    @GetMapping("/{id_contractor}")
    @Operation(summary = "Get All Users Favorites", description = "Listar todos os favoritos.", tags = "PROFILE")
    public ResponseEntity<List<User>> findAllFavorites(@PathVariable int id_contractor) {
        List<User> users = favoriteRepository.findByFavorite(id_contractor);
        return ResponseEntity.status(200).body(users);
    }
}
