package sptech.faztudo.comLOCAL.assessments;

import jakarta.persistence.*;
import sptech.faztudo.comLOCAL.users.domain.users.User;

@Entity
public class Favorite {

    @Id
    @Column(unique = true, name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private int fkContractor;

    private int fkProvider;

    public Favorite(Integer fkContractor, Integer fkProvider) {
        this.fkContractor = fkContractor;
        this.fkProvider = fkProvider;
    }

    public Favorite() {

    }


    public Integer getFkContractor() {
        return fkContractor;
    }

    public void setFkContractor(Integer fkContractor) {
        this.fkContractor = fkContractor;
    }

    public Integer getFkProvider() {
        return fkProvider;
    }

    public void setFkProvider(Integer fkProvider) {
        this.fkProvider = fkProvider;
    }
}
