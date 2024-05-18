package sptech.faztudo.comLOCAL.users.domain.serviceProvider;

import jakarta.persistence.*;
import sptech.faztudo.comLOCAL.users.UserRole;
import sptech.faztudo.comLOCAL.users.domain.users.User;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class ServiceProvider extends User {

    @ManyToOne
    private Category category;

    public ServiceProvider(String name,
                           String lastName,
                           String cpf,
                           LocalDate dt_nascimento,
                           String cep,
                           String logradouro,
                           String state,
                           String city,
                           String phone,
                           String email,
                           String senha,
                           LocalDateTime dt_cadastro,
                           String descricao,
                           Category category,
                           byte[] image_profile,

                           UserRole role) {
        super( name, lastName, cpf, dt_nascimento, cep, logradouro, state, city, phone, email, senha,dt_cadastro,descricao,image_profile, role);
        this.category = category;
    }


    public ServiceProvider(){
      
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }


}
