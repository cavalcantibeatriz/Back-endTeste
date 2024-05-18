package sptech.faztudo.comLOCAL.post.repositoryPost;

import org.springframework.data.jpa.repository.JpaRepository;
import sptech.faztudo.comLOCAL.post.domainPost.upload.ContractorPost;

import java.util.List;

public interface ContractorPostRepository extends JpaRepository <ContractorPost, Long> {

    List<ContractorPost> findAllByFkContractor(Integer id);

    ContractorPost findById(Integer id);

    List<ContractorPost> findAllByFkProvider(Integer id);
}
