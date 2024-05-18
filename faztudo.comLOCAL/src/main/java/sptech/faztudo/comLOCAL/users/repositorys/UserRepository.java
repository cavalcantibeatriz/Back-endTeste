package sptech.faztudo.comLOCAL.users.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import sptech.faztudo.comLOCAL.users.domain.serviceProvider.ServiceProvider;
import sptech.faztudo.comLOCAL.users.domain.users.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findAll();
    User findById(int id);
    UserDetails findByEmail(String email);





//    @Transactional
//    @Modifying
//    @Query("UPDATE user u " +
//            "SET u.enabled = TRUE WHERE u.email = ?1")
//    int enableAppUser(String email);
}

