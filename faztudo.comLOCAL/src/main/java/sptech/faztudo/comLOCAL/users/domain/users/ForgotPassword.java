package sptech.faztudo.comLOCAL.users.domain.users;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ForgotPassword {
    @Email
    @NotBlank
    private String email;
}
