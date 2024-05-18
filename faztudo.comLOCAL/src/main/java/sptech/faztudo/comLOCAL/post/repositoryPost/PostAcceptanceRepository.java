package sptech.faztudo.comLOCAL.post.repositoryPost;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sptech.faztudo.comLOCAL.post.domainPost.upload.ContractorPost;
import sptech.faztudo.comLOCAL.post.domainPost.upload.PostAcceptance;

import java.util.List;
import java.util.Optional;

public interface PostAcceptanceRepository extends JpaRepository<PostAcceptance, Long> {

    @Query(value = "select p from PostAcceptance p where p.Post.id=:post and p.Prestador.id =:prestador")
    Optional<PostAcceptance> checarPostagemExistente(Long post, Integer prestador);


}
