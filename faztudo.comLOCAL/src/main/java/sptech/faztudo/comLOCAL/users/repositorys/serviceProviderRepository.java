package sptech.faztudo.comLOCAL.users.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import sptech.faztudo.comLOCAL.users.domain.serviceProvider.Category;
import sptech.faztudo.comLOCAL.users.domain.serviceProvider.ServiceProvider;

import java.util.List;

public interface serviceProviderRepository extends JpaRepository<ServiceProvider, Integer> {

    List<ServiceProvider> findAll();
    ServiceProvider findById(int id);
    UserDetails findByEmail(String email);


}
