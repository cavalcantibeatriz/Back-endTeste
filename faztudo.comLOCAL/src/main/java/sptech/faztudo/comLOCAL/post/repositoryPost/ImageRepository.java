package sptech.faztudo.comLOCAL.post.repositoryPost;

import org.springframework.data.jpa.repository.JpaRepository;
import sptech.faztudo.comLOCAL.post.domainPost.upload.Image;

import java.util.List;
import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, Long>  {

    List<Image> findAllByFkUser(Long userId);

    Optional<Image> findByTipoAndFkUser(Integer imageId, Integer userID);
}
