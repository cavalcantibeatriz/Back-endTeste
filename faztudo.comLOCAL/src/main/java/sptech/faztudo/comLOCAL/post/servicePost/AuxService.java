package sptech.faztudo.comLOCAL.post.servicePost;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sptech.faztudo.comLOCAL.post.domainPost.upload.ContractorPost;
import sptech.faztudo.comLOCAL.post.domainPost.upload.PostAcceptance;
import sptech.faztudo.comLOCAL.post.repositoryPost.ContractorPostRepository;
import sptech.faztudo.comLOCAL.post.repositoryPost.PostAcceptanceRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AuxService {

    @Autowired
    private ContractorPostRepository contractorPostRepository;

    @Autowired
    private PostAcceptanceRepository PostAcceptanceRepository;


    public void patchInteressePostado(Long idPost) {

        ContractorPost patch = null;

        Optional<ContractorPost> contractorPostOptional = contractorPostRepository.findById(idPost);

        patch = (contractorPostOptional.get());

        patch.setStatus("Interesse enviado por algum prestador de serviço");

        contractorPostRepository.save(patch);

    }

    public void patchInteresseAceito(Long idPost, Integer fkProvider) {

        ContractorPost patch = null;

        Optional<ContractorPost> contractorPostOptional = contractorPostRepository.findById(idPost);

        patch = (contractorPostOptional.get());

        patch.setStatus("Negócio fechado!");

        patch.setFkProvider(fkProvider);

        contractorPostRepository.save(patch);


    }

    public void patchInteresseNegado(Long idPost) {

        ContractorPost patch = null;

        Optional<ContractorPost> contractorPostOptional = contractorPostRepository.findById(idPost);

        patch = (contractorPostOptional.get());

        patch.setStatus("Interesse recusado! - Aguardando novos interesses!");

        contractorPostRepository.save(patch);


    }

    public void patchDemandaFinalizada(Integer idPost, String Avaliacao, Integer Rating) {

        ContractorPost patch = null;

        ContractorPost contractorPostOptional = contractorPostRepository.findById(idPost);

        patch = contractorPostOptional;

        patch.setStatus("Demanda finalizada!");

        patch.setDataDeConclusao(LocalDateTime.now());

        patch.setAvaliacao(Avaliacao);

        patch.setRating(Rating);

        contractorPostRepository.save(patch);

    }

    public List<PostAcceptance> recuperarTodasDemandas( ){


//        ContractorPost demanda = contractorPostRepository.findById(idPost);
//        Optional<PostAcceptance> demanda = PostAcceptanceRepository.findById(idPost);
//        return demanda;
        return PostAcceptanceRepository.findAll();


    }

}