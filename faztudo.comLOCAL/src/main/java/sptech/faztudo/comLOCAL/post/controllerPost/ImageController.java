package sptech.faztudo.comLOCAL.post.controllerPost;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sptech.faztudo.comLOCAL.post.domainPost.upload.Image;
import sptech.faztudo.comLOCAL.post.domainPost.upload.ImageDTO;
import sptech.faztudo.comLOCAL.post.repositoryPost.ImageRepository;
import sptech.faztudo.comLOCAL.users.domain.users.User;
import sptech.faztudo.comLOCAL.users.repositorys.UserRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("images")
public class ImageController {

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/upload/{tipo}/{user}")
    @Operation(summary = "Imagens Geral", description = "Envio de imagens, uso geral, distinção por ID de usuario e TIPO de imagen", tags = "IMAGES")
    public ResponseEntity<Long> uploadImage(@RequestParam("file") MultipartFile file, @PathVariable Integer tipo, @PathVariable Integer user) throws IOException {

        Optional<User> optionalUser = userRepository.findById(user);

        if (tipo == 1) {
            Optional<Image> optionalImage = imageRepository.findByTipoAndFkUser(tipo, user);
            if (optionalImage.isPresent()) {
                return ResponseEntity.status(409).build();
            }
        }

        if (optionalUser.isPresent()) {
            Image image = new Image();
            image.setName(file.getOriginalFilename());
            System.out.println(file.getOriginalFilename());
            image.setData(file.getBytes());
            image.setTipo(tipo);
            image.setFkUser(user);

            Image savedImage = imageRepository.save(image);

            Long idDaImagem = savedImage.getId();

            return ResponseEntity.status(201).body(idDaImagem);
        } else {
            return ResponseEntity.status(404).build();
        }

    }

    @GetMapping("/get/{id}")
    @Operation(summary = "Lista de Imagens", description = "Recupera as imagens enviadas por ID de user", tags = "IMAGES")
    public ResponseEntity<List<ImageDTO>> getImages(@PathVariable Long id) {

        List<Image> imagens = imageRepository.findAllByFkUser(id);

        if (imagens.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<ImageDTO> imagensDTO = new ArrayList<>();

        for (Image imagem : imagens) {

            String base64Data = Base64.getEncoder().encodeToString(imagem.getData());
            String nome = imagem.getName();
            Integer imagemTipo = imagem.getTipo();
            Long imagemId = imagem.getId();
            Integer fkUser = imagem.getFkUser();

            ImageDTO imagemDTO = new ImageDTO(base64Data, nome, imagemTipo, imagemId, fkUser); // Passando o ID da imagem para o construtor do DTO
            imagensDTO.add(imagemDTO);
        }

        ResponseEntity responseEntity = new ResponseEntity<>(imagensDTO, HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping("/get/all")
    @Operation(summary = "Lista de Imagens", description = "Recupera todas as imagens do banco", tags = "IMAGES")
    public ResponseEntity<List<ImageDTO>> getAllImages() {

        List<Image> imagens = imageRepository.findAll();

        if (imagens.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<ImageDTO> imagensDTO = new ArrayList<>();

        for (Image imagem : imagens) {

            String base64Data = Base64.getEncoder().encodeToString(imagem.getData());
            String nome = imagem.getName();
            Integer imagemTipo = imagem.getTipo();
            Integer fkUser = imagem.getFkUser();

            ImageDTO imagemDTO = new ImageDTO(base64Data, nome, imagemTipo, fkUser);
            imagensDTO.add(imagemDTO);
        }

        return new ResponseEntity<>(imagensDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Lista de Imagens", description = "Apaga a imagem", tags = "IMAGES")
    public ResponseEntity<String> deleteImage(@PathVariable Long id) {

        if (imageRepository.existsById(id)) {

            imageRepository.deleteById(id);
            return ResponseEntity.ok("Imagem removida com sucesso.");
        } else {

            return ResponseEntity.notFound().build();
        }
    }


}
