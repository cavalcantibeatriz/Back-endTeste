package sptech.faztudo.comLOCAL.post.controllerPost;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import sptech.faztudo.comLOCAL.post.domainPost.upload.ContractorPost;
import sptech.faztudo.comLOCAL.post.domainPost.upload.PostAcceptance;
import sptech.faztudo.comLOCAL.post.repositoryPost.ContractorPostRepository;
import sptech.faztudo.comLOCAL.post.servicePost.AuxService;
import sptech.faztudo.comLOCAL.users.domain.serviceProvider.ServiceProvider;
import sptech.faztudo.comLOCAL.users.repositorys.serviceProviderRepository;
import sptech.faztudo.comLOCAL.post.repositoryPost.PostAcceptanceRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/proposta")
public class PostAcceptanceController {

    @Autowired
    private PostAcceptanceRepository postAcceptanceRepository;

    @Autowired
    private ContractorPostRepository contractorPostRepository;

    @Autowired
    private serviceProviderRepository serviceProviderRepository;

    @Autowired
    private AuxService auxService;


    @PostMapping("/criar/{idPost}/{idProvider}")
    @Operation(summary = "criarInteresse", description = "Demonstra interesse na demanda de algum contratante", tags = "POST - POST ACCEPTANCE")
    public ResponseEntity<PostAcceptance> criarAcceptancePost(@RequestBody PostAcceptance postAcceptance, @PathVariable Long idPost, @PathVariable Integer idProvider) {

        Optional<PostAcceptance> optional = postAcceptanceRepository.checarPostagemExistente(idPost,idProvider);

        if (optional.isEmpty()) {

            Optional<ContractorPost> demanda = contractorPostRepository.findById(idPost);

            Optional<ServiceProvider> prestador = serviceProviderRepository.findById(idProvider);

            if (demanda.isEmpty() || prestador.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

            postAcceptance.setPost(demanda.get());
            postAcceptance.setPrestador(prestador.get());
            postAcceptance.setStatus("Aguardando resposta do contratante.");

            PostAcceptance newPostAcceptance = postAcceptanceRepository.save(postAcceptance);

            auxService.patchInteressePostado(idPost);

            return ResponseEntity.status(HttpStatus.CREATED).body(newPostAcceptance);

        }

        PostAcceptance Existe = optional.get();

        System.out.println(Existe);

        return ResponseEntity.status(HttpStatus.CONFLICT).build();

    }

    @PatchMapping("/atualizar/{idPostAcceptance}")
    @Operation(summary = "atualizarInteresse", description = "Atualiza a mensagem do interesse já enviado", tags = "POST - POST ACCEPTANCE")
    public ResponseEntity<PostAcceptance> atualizarAcceptancePost(@RequestBody Map<String, Object> updates, @PathVariable Long idPostAcceptance) {

        Optional<PostAcceptance> post = postAcceptanceRepository.findById(idPostAcceptance);

        if (post.isPresent()) {

            PostAcceptance postAcceptance = post.get();

            if (updates.containsKey("mensagem")) {

                postAcceptance.setMensagem((String) updates.get("mensagem"));

            }

            PostAcceptance postAtualizado = postAcceptanceRepository.save(postAcceptance);

            return ResponseEntity.status(HttpStatus.CREATED).body(postAtualizado);

        } else {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        }
    }

    @PatchMapping("/aceitar/{idPostAcceptance}")
    @Operation(summary = "aceitarInteresse", description = "Aceitar o interesse enviado por algum prestador", tags = "POST - POST ACCEPTANCE")
    public ResponseEntity<PostAcceptance> aceitarDemanda(@PathVariable Long idPostAcceptance)  {

        Optional<PostAcceptance> optional = postAcceptanceRepository.findById(idPostAcceptance);

        if (optional.isPresent()){

        auxService.patchInteresseAceito(optional.get().getPost().getId(),optional.get().getPrestador().getId());

        PostAcceptance obj = optional.get();

        obj.setStatus("Interesse aceito pelo contratante!");

        postAcceptanceRepository.save(obj);

        } else {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        }

        return ResponseEntity.status(HttpStatus.ACCEPTED).build();


    }

    @PatchMapping("/recusar/{idPostAcceptance}")
    @Operation(summary = "recusarInteresse", description = "Negar o interesse enviado por algum prestador", tags = "POST - POST ACCEPTANCE")
    public ResponseEntity<PostAcceptance> recusarDemanda(@PathVariable Long idPostAcceptance)  {

        Optional<PostAcceptance> optional = postAcceptanceRepository.findById(idPostAcceptance);

        if (optional.isPresent()){

            auxService.patchInteresseNegado(optional.get().getPost().getId());

            PostAcceptance obj = optional.get();

            obj.setStatus("Interesse recusado pelo contratante!");

            postAcceptanceRepository.save(obj);

        } else {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        }

        return ResponseEntity.status(HttpStatus.ACCEPTED).build();


    }

    @GetMapping("/notificar")
    @Operation(summary = "recuperarDemanda", description = "Recuperar ass informaçoes de demanda", tags = "POST - POST ACCEPTANCE")
    public ResponseEntity<List<PostAcceptance>> recuperarTodasDemandas() {

        List<PostAcceptance> optional = auxService.recuperarTodasDemandas();

        if (!optional.isEmpty()) {

            return ResponseEntity.status(HttpStatus.ACCEPTED).body(optional);

        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }


}
