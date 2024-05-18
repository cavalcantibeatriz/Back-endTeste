package sptech.faztudo.comLOCAL.users.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sptech.faztudo.comLOCAL.users.domain.users.User;

import java.util.Optional;

@Repository
public interface ForgotPasswordRepository extends JpaRepository<User, Integer> {


    User findByEmail(String email);
}
