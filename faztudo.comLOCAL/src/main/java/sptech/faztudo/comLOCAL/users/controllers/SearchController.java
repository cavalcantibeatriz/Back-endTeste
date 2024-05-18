package sptech.faztudo.comLOCAL.users.controllers;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sptech.faztudo.comLOCAL.users.domain.serviceProvider.ServiceProvider;
import sptech.faztudo.comLOCAL.users.domain.users.User;
import sptech.faztudo.comLOCAL.users.repositorys.UserRepository;
import sptech.faztudo.comLOCAL.users.repositorys.serviceProviderRepository;

import java.util.List;

@Controller
@RequestMapping("search")
public class SearchController {

    @Autowired
    private serviceProviderRepository repository;

    @GetMapping("/")
    @Operation(summary = "Get All Providers", description = "Listar todos os prestadores.", tags = "SEARCH")
    public ResponseEntity<List<ServiceProvider>> findAll() {
        List<ServiceProvider> users = repository.findAll();
        return ResponseEntity.status(200).body(users);

    }

}
