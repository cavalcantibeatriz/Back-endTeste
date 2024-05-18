package sptech.faztudo.comLOCAL.post.controllerPost;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sptech.faztudo.comLOCAL.post.domainPost.upload.ContractorPost;
import sptech.faztudo.comLOCAL.post.repositoryPost.ContractorPostRepository;
import sptech.faztudo.comLOCAL.users.services.FilaService;
import sptech.faztudo.comLOCAL.users.services.PilhaService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/provider-post")
public class ProviderPostController {

    @Autowired
    private ContractorPostRepository contractorPostRepository;

    @Autowired
    private PilhaService<ContractorPost> pilhaService;
    @Autowired
    private FilaService<ContractorPost> filaService;

    @GetMapping("/{idProvider}")
    @Operation(summary = "Post Contratante", description = "Recupera todas as postagens através da fk do provedor.", tags = "USER - PROVIDER - POST")
    public ResponseEntity<List<ContractorPost>> obterProviderPost(@PathVariable Integer idProvider) {

        List<ContractorPost> contractorPosts = contractorPostRepository.findAllByFkProvider(idProvider);

        if (!contractorPosts.isEmpty()) {

            return ResponseEntity.ok(contractorPosts);

        } else {

            return ResponseEntity.notFound().build();

        }
    }

    @PatchMapping("/{idPost}/{idProvider}")
    @Operation(summary = "Post Contratante", description = "Aceita a demanda de trabalho.", tags = "USER - PROVIDER - PATCH")
    public ResponseEntity<ContractorPost> aceitarContractorPost(@PathVariable Integer idPost, @PathVariable Integer idProvider) {
        ContractorPost post = contractorPostRepository.findById(idPost);

        post.setFkProvider(idProvider);

        contractorPostRepository.save(post);

        pilhaService.push(post);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(post);
    }

    @PatchMapping("/{idPost}")
    @Operation(summary = "Post Contratante", description = "Finaliza a demanda de trabalho.", tags = "USER - PROVIDER - PATCH")
    public ResponseEntity<ContractorPost> finalizaContractorPost(@PathVariable Integer idPost) {



        if (filaService.isFull()){
        filaService.poll();
        }
        filaService.insert(pilhaService.peek());
        pilhaService.pop();

        ContractorPost post = contractorPostRepository.findById(idPost);

        post.setDataDeConclusao(LocalDateTime.now());

        contractorPostRepository.save(post);

        filaService.exibe();

        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }




}
