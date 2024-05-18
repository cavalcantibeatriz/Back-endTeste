package sptech.faztudo.comLOCAL.users.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import sptech.faztudo.comLOCAL.users.domain.contractor.Contractor;


import java.util.List;

public interface contractorRepository extends JpaRepository<Contractor, Integer> {

    List<Contractor> findAll();
    Contractor findById(int id);
    UserDetails findByEmail(String email);
}
