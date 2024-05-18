package sptech.faztudo.comLOCAL.post.domainPost.upload;

import jakarta.persistence.*;
import sptech.faztudo.comLOCAL.users.domain.serviceProvider.ServiceProvider;

import java.security.Provider;
import java.util.Optional;

@Entity
public class PostAcceptance {

    @Id
    @Column(unique = true, name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "fk_prestador")
    private ServiceProvider Prestador;

    @ManyToOne
    @JoinColumn(name = "fk_demanda")
    private ContractorPost Post;

    @Column
    private String mensagem;

    @Column
    private String status;

    public PostAcceptance() {
    }

    public PostAcceptance(Long id, ServiceProvider prestador, ContractorPost post, String mensagem, String status) {
        this.id = id;
        Prestador = prestador;
        Post = post;
        this.mensagem = mensagem;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ServiceProvider getPrestador() {
        return Prestador;
    }

    public void setPrestador(ServiceProvider prestador) {
        Prestador = prestador;
    }

    public ContractorPost getPost() {
        return Post;
    }

    public void setPost(ContractorPost post) {
        Post = post;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
