package sptech.faztudo.comLOCAL.post.domainPost.upload;

public class ImageDTO {
    private String base64Data;
    private String nome;
    private Integer tipo;
    private Long id;
    private Integer fkUser;

    public ImageDTO(String base64Data, String nome, Integer tipo, Long id, Integer fkUser) {
        this.base64Data = base64Data;
        this.nome = nome;
        this.tipo = tipo;
        this.id = id;
        this.fkUser = fkUser;
    }

    public ImageDTO(String base64Data, String nome, Integer tipo, Integer fkUser) {
        this.base64Data = base64Data;
        this.nome = nome;
        this.tipo = tipo;
        this.fkUser = fkUser;
    }

    public String getBase64Data() {
        return base64Data;
    }

    public void setBase64Data(String base64Data) {
        this.base64Data = base64Data;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public Integer getFkUser() {
        return fkUser;
    }

    public void setFkUser(Integer fkUser) {
        this.fkUser = fkUser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
