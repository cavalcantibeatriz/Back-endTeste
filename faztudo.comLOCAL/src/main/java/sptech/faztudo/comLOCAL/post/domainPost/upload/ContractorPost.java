package sptech.faztudo.comLOCAL.post.domainPost.upload;

import jakarta.persistence.*;
import sptech.faztudo.comLOCAL.users.domain.users.User;

import java.time.LocalDateTime;

@Entity
@Table(name = "contractor_post")
public class ContractorPost {

    @Id
    @Column(unique = true, name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fk_contractor")
    private Integer fkContractor;

    @Column(name = "fk_provider")
    private Integer fkProvider;

    @Column
    private String descricao;

    @Column
    private String avaliacao;

    @Column
    private String status;

    @Column
    private Integer categoria;

    @Column
    private Integer rating;

    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;

    @Column(name = "data_conclusao")
    private LocalDateTime dataDeConclusao;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(columnDefinition = "longblob", name = "imagem")
    private byte[] data;


    public ContractorPost() {
    }

    public ContractorPost(Long id,
                          Integer fkContractor,
                          Integer fkProvider,
                          String descricao,
                          String avaliacao,
                          String status,
                          Integer categoria,
                          Integer rating,
                          LocalDateTime dataCriacao,
                          LocalDateTime dataDeConclusao,
                          byte[] data) {
        this.id = id;
        this.fkContractor = fkContractor;
        this.fkProvider = fkProvider;
        this.descricao = descricao;
        this.avaliacao = avaliacao;
        this.status = status;
        this.categoria = categoria;
        this.rating = rating;
        this.dataCriacao = dataCriacao;
        this.dataDeConclusao = dataDeConclusao;
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(String avaliacao) {
        this.avaliacao = avaliacao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getCategoria() {
        return categoria;
    }

    public void setCategoria(Integer categoria) {
        this.categoria = categoria;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDateTime getDataDeConclusao() {
        return dataDeConclusao;
    }

    public void setDataDeConclusao(LocalDateTime dataDeConclusao) {
        this.dataDeConclusao = dataDeConclusao;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
