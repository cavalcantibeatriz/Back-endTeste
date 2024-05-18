package sptech.faztudo.comLOCAL.users.domain.users;


import jakarta.persistence.*;


import jakarta.validation.constraints.*;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import sptech.faztudo.comLOCAL.users.UserRole;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "user")
@Setter
public class User implements UserDetails {


    @Id
    @Column(unique = true, name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    @Size(min = 5, max = 50, message = "Nome com tamanho de inválido")
    @NotBlank(message = "Preencha o campo nome corretamente. ")
    private String name;

    @Column(name = "lastName", nullable = false)
    @Size(min = 5, max = 150, message = "Sobrenome com tamanho inválido")
    @NotBlank(message = "Preencha o campo sobrenome corretamente. ")
    private String lastName;

    @Column(name = "cpf", nullable = false, unique = true, length = 11)
    @CPF(message = "CPF inválido!")
    @NotNull
    private String cpf;

    @Column(name = "dt_nascimento")
    @NotNull
    @Past
    @AgeConstraint(minAge = 18)
    private LocalDate dt_nascimento;

    @Column(name = "cep", nullable = false)
    @NotNull
    private String cep;

    @Column(name = "logradouro", nullable = false)
    private String logradouro;

    @Column(name = "state")
    private String state;

    @Column(name = "city")
    private String city;

    @Column(name = "phone", unique = true, nullable = false)
//    @Pattern(regexp = "\\([0-9]{2}\\) [0-9]{4,5}-[0-9]{4}", message = "Telefone inválido")
    @NotNull
    private String phone;

    @Column(name = "email", unique = true, nullable = false)
    @Email(message = "Email inválido!")
    @NotNull
    private String email;

    @Column(name = "senha", nullable = false)
    @Pattern.List({
            @Pattern(regexp = ".*[A-Z].*", message = "A senha deve conter pelo menos uma letra maiúscula"),
            @Pattern(regexp = ".*[a-z].*", message = "A senha deve conter pelo menos uma letra minúscula"),
            @Pattern(regexp = ".*\\d.*", message = "A senha deve conter pelo menos um dígito"),
            @Pattern(regexp = "(?=.*[!@#$%^&*()]).{8,}$", message = "A senha deve conter pelo menos um caractere especial e 8 caracteres.")
    })
    @NotNull
    private String senha;

    @Column(name = "dt_cadastro")
    private LocalDateTime dt_cadastro;

    @Column(name = "descricao_perfil")
    private String descricao;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(columnDefinition = "longblob")
    private byte[] image_profile;

    @Column(name = "role")
    private UserRole role;

    public User() {

    }


    public void setId(int id) {
        this.id = id;
    }


    public User(String name, String lastName, String cpf, LocalDate dt_nascimento, String cep, String logradouro, String state, String city, String phone, String email, String senha, LocalDateTime dt_cadastro, String descricao,  byte[] image_profile) {
        this.name = name;
        this.lastName = lastName;
        this.cpf = cpf;
        this.dt_nascimento = dt_nascimento;
        this.cep = cep;
        this.logradouro = logradouro;
        this.state = state;
        this.city = city;
        this.phone = phone;
        this.email = email;
        this.senha = senha;
        this.dt_cadastro = dt_cadastro;
        this.descricao = descricao;
        this.image_profile = image_profile;
    }

    public User(String name, String lastName, String cpf, LocalDate dt_nascimento, String cep, String logradouro, String state, String city, String phone, String email, String senha, LocalDateTime dt_cadastro, String descricao,  byte[] image_profile, UserRole role) {
        this.name = name;
        this.lastName = lastName;
        this.cpf = cpf;
        this.dt_nascimento = dt_nascimento;
        this.cep = cep;
        this.logradouro = logradouro;
        this.state = state;
        this.city = city;
        this.phone = phone;
        this.email = email;
        this.senha = senha;
        this.dt_cadastro = dt_cadastro;
        this.descricao = descricao;
        this.image_profile = image_profile;
        this.role = role;
    }



    public User(Integer id,
                String name,
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
                byte[] image_profile,
                UserRole role) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.cpf = cpf;
        this.dt_nascimento = dt_nascimento;
        this.cep = cep;
        this.logradouro = logradouro;
        this.state = state;
        this.city = city;
        this.phone = phone;
        this.email = email;
        this.senha = senha;
        this.dt_cadastro = dt_cadastro;
        this.descricao = descricao;
        this.image_profile = image_profile;
        this.role = role;
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDt_nascimento() {
        return dt_nascimento;
    }

    public void setDt_nascimento(LocalDate dt_nascimento) {
        this.dt_nascimento = dt_nascimento;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public LocalDateTime getDt_cadastro() {
        return dt_cadastro;
    }

    public void setDt_cadastro(LocalDateTime dt_cadastro) {
        this.dt_cadastro = dt_cadastro;
    }

    public String getDescricao() {
        return descricao;
    }

    public byte[] getImage_profile() {
        return image_profile;
    }

    public void setImage_profile(byte[] image_profile) {
        this.image_profile = image_profile;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.role == UserRole.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}

