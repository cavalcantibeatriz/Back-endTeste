package sptech.faztudo.comLOCAL.users.controllers;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import sptech.faztudo.comLOCAL.users.domain.files.FileManager;
import sptech.faztudo.comLOCAL.users.domain.users.User;
import sptech.faztudo.comLOCAL.users.repositorys.UserRepository;

import java.util.List;
@RestController

public class FileController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/csv/save")
    @Operation(summary = "Save CSV", description = "Listar todos os usuarios e salvar um CSV", tags = "BACKOFFICE")
    public ResponseEntity<List<User>> salvarCSV() {

        List<User> users = userRepository.findAll();

        FileManager.gravarCSV(users, "ArquivoCSV");

        return ResponseEntity.status(200).body(users);

    }

    @GetMapping("/txt/save")
    @Operation(summary = "Save TXT", description = "Listar todos os usuarios e salvar um TXT", tags = "BACKOFFICE")
    public ResponseEntity<List<User>> salvarTXT() {

        List<User> users = userRepository.findAll();

        FileManager.gravaArquivoTxt(users, "ArquivoTXT");

        return ResponseEntity.status(200).body(users);

    }

    @PostMapping("/csv/order")
    @Operation(summary = "Order CSV", description = "Ler o CSV de todos os usuários", tags = "BACKOFFICE")
    public ResponseEntity<List<User>> ordenarCSV() {

        try {
            List<User> users = FileManager.ordenarCSV("ArquivoCSV");

            return ResponseEntity.status(200).body(users);

        } catch (Exception e) {

            return ResponseEntity.status(400).build();
     }
    }

    @GetMapping("/csv/find/{nome}")
    @Operation(summary = "Order CSV", description = "Ler o CSV de todos os usuários", tags = "BACKOFFICE")
    public ResponseEntity<ResponseEntity<User>> acharCSV(@PathVariable String nome) {

        try {
            ResponseEntity<User> info = FileManager.acharCSV("ArquivoCSVOrdenado", nome);

            return ResponseEntity.status(200).body(info);

        } catch (Exception e) {

            return ResponseEntity.status(400).build();
        }
    }


    @PostMapping("/txt/import")
    @Operation(summary = "Import TXT", description = "Ler o TXT dos usuários e importar para o BD", tags = "BACKOFFICE")
    public ResponseEntity<List<User>> importarTXT() {
        try {

            System.out.println("tentei o gerenciador ");

            List<User> users = FileManager.leArquivoTxt("ArquivoTXT");

            for (User a : users){

                userRepository.save(a);
            }

            return ResponseEntity.status(200).body(users);
        } catch (Exception e) {
            return ResponseEntity.status(400).build();
        }
    }

}
