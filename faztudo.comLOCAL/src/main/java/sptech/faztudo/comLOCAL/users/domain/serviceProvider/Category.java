package sptech.faztudo.comLOCAL.users.domain.serviceProvider;


import jakarta.persistence.*;


import jakarta.validation.constraints.*;
import lombok.Setter;

@Entity
@Table(name = "categories")
@Setter
public class Category {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false)
    @NotBlank(message = "Não pode ser nulo")
    private String name;


    public Category() {

    }
    public Category(String name) {
        this.name = name;
    }
    public void setId(int id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /*Category categoria1 = new Category("Elétrica");
    Category categoria2 = new Category("Limpeza");
    Category categoria3 = new Category("Mecânico");
    Category categoria4 = new Category("Hidráulica");
    Category categoria5 = new Category("Obras");*/

}

