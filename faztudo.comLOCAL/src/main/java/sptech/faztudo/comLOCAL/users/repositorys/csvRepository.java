package sptech.faztudo.comLOCAL.users.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import sptech.faztudo.comLOCAL.users.domain.users.User;

import java.util.List;

public interface csvRepository extends JpaRepository<User, Integer> {

    List<User> findAll();




}
