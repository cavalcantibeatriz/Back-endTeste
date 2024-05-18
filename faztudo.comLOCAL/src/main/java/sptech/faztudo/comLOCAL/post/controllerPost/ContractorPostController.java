package sptech.faztudo.comLOCAL.post.controllerPost;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sptech.faztudo.comLOCAL.post.domainPost.upload.ContractorPost;
import sptech.faztudo.comLOCAL.post.domainPost.upload.Image;
import sptech.faztudo.comLOCAL.post.domainPost.upload.PostAcceptance;
import sptech.faztudo.comLOCAL.post.repositoryPost.ContractorPostRepository;
import sptech.faztudo.comLOCAL.post.repositoryPost.ImageRepository;
import sptech.faztudo.comLOCAL.post.servicePost.AuxService;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/post")
public class ContractorPostController {

    @Autowired
    private ContractorPostRepository contractorPostRepository;
    @Autowired
    private AuxService auxService;


    @PostMapping("/{idContractor}")
    @Operation(summary = "Post Contratante", description = "Postagem para contratante, envia descrição do trabalho e imagem.", tags = "USER - CONTRACTOR - POST")
    public ResponseEntity<ContractorPost> criarContractorPost(@RequestBody ContractorPost contractorPost, @PathVariable Integer idContractor) {

        contractorPost.setDataCriacao(LocalDateTime.now());

        contractorPost.setFkContractor(idContractor);

        contractorPost.setStatus("Recem criado");

        ContractorPost novoContractorPost = contractorPostRepository.save(contractorPost);

        return ResponseEntity.status(HttpStatus.CREATED).body(novoContractorPost);
    }

    @PatchMapping("/finalizar/{idPost}")
    @Operation(summary = "Post Contratante", description = "Finalizar postagem, depois do interesse aceito!", tags = "USER - CONTRACTOR - POST")
    public ResponseEntity<ContractorPost> finalizarContractorPost(@RequestBody Map<String, Object> updates,  @PathVariable Integer idPost) {

        ContractorPost post = contractorPostRepository.findById(idPost);

        if (post != null) {

            String avaliacao = (String) updates.get("avaliacao");
            String ratingString = (String) updates.get("rating");

            Integer rating = Integer.valueOf(ratingString);

            auxService.patchDemandaFinalizada(idPost, avaliacao, rating);

            return ResponseEntity.status(HttpStatus.ACCEPTED).build();

        } else {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

    @GetMapping("/all")
    @Operation(summary = "Post Contratante", description = "Recupera todas as postagens do site", tags = "USER - CONTRACTOR - POST")
    public ResponseEntity<List<ContractorPost>> obterAllPost() {

        List<ContractorPost> contractorPosts = contractorPostRepository.findAll();

        if (!contractorPosts.isEmpty()) {

            return ResponseEntity.ok(contractorPosts);

        } else {

            return ResponseEntity.notFound().build();

        }
    }

    @GetMapping("/{idContractor}")
    @Operation(summary = "Post Contratante", description = "Recupera todas as postagens através do id do usuário.", tags = "USER - CONTRACTOR - POST")
    public ResponseEntity<List<ContractorPost>> obterContractorPost(@PathVariable Integer idContractor) {

        List<ContractorPost> contractorPosts = contractorPostRepository.findAllByFkContractor(idContractor);

        if (!contractorPosts.isEmpty()) {

            return ResponseEntity.ok(contractorPosts);

        } else {

            return ResponseEntity.notFound().build();

        }
    }

    @PatchMapping("/atualizar/{idPost}")
    @Operation(summary = "Post Contratante", description = "Atualiza as postagens através do id do post.", tags = "USER - CONTRACTOR - POST")
    public ResponseEntity<ContractorPost> atualizarContractorPost(@PathVariable Long idPost, @RequestBody Map<String, Object> updates) {

        Optional<ContractorPost> contractorPostOptional = contractorPostRepository.findById(idPost);

        if (contractorPostOptional.isPresent()) {

            ContractorPost contractorPost = contractorPostOptional.get();

            if (updates.containsKey("descricao")) {

                contractorPost.setDescricao((String) updates.get("descricao"));

            }

            if (updates.containsKey("categoria")) {

                contractorPost.setCategoria(Integer.valueOf((String) updates.get("categoria")));

            }

            if (updates.containsKey("fk_provider")) {

                contractorPost.setFkProvider(Integer.valueOf((String) updates.get("fk_provider")));

            }

            if (updates.containsKey("avaliacao")) {

                contractorPost.setAvaliacao((String) updates.get("avaliacao"));

            }

            if (updates.containsKey("status")) {

                contractorPost.setStatus((String) updates.get("status"));

                if (updates.get("status").equals("finalizado")){

                    contractorPost.setDataDeConclusao(LocalDateTime.now());

                }

            }

            if (updates.containsKey("rating")) {

                contractorPost.setRating(Integer.valueOf((String) updates.get("rating")));

            }

            ContractorPost contractorPostAtualizado = contractorPostRepository.save(contractorPost);

            return ResponseEntity.ok(contractorPostAtualizado);

        } else {

            return ResponseEntity.notFound().build();

        }
    }

    @PatchMapping("/atrelar/{idPost}")
    @Operation(summary = "Post Contratante", description = "Atrela uma imagem a postagem.", tags = "USER - CONTRACTOR - POST")
    public ResponseEntity<ContractorPost> atrelarImagem(@RequestParam("file") MultipartFile file, @PathVariable Integer idPost) throws IOException {

        Optional<ContractorPost> contractorPostOptional = Optional.ofNullable(contractorPostRepository.findById(idPost));

        System.out.println(contractorPostOptional);
        

        if (contractorPostOptional.isPresent()) {

            ContractorPost contractorPost = contractorPostOptional.get();

            contractorPost.setData(file.getBytes());

            ContractorPost contractorPostAtualizado = contractorPostRepository.save(contractorPost);

            return ResponseEntity.ok(contractorPostAtualizado);

        } else {

            return ResponseEntity.notFound().build();

        }

    }

    @DeleteMapping("/{idPost}")
    @Operation(summary = "Post Contratante", description = "Deleta postagens através do id do post.", tags = "USER - CONTRACTOR")
    public ResponseEntity<Void> excluirContractorPost(@PathVariable Long idPost) {

        Optional<ContractorPost> contractorPostOptional = contractorPostRepository.findById(idPost);

        if (contractorPostOptional.isPresent()) {

            ContractorPost contractorPost = contractorPostOptional.get();

            contractorPostRepository.delete(contractorPost);

            return ResponseEntity.noContent().build();

        } else {

            return ResponseEntity.notFound().build();

        }
    }
}
