package sptech.faztudo.comLOCAL.post.domainPost.upload;

import jakarta.persistence.*;
import sptech.faztudo.comLOCAL.users.domain.users.User;

import java.util.Optional;

@Entity
@Table(name="images")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "fk_user")
    private Integer fkUser;

    @Column(name ="name")
    private String name;

    @Column(name = "tipo")
    private Integer tipo;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(columnDefinition = "longblob")
    private byte[] data;

    public Image() {
    }

    public Image(Long id, Integer fkUser, String name, Integer tipo, byte[] data) {
        this.id = id;
        this.fkUser = fkUser;
        this.name = name;
        this.tipo = tipo;
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    public Integer getFkUser() {
        return fkUser;
    }

    public void setFkUser(Integer fkUser) {
        this.fkUser = fkUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
