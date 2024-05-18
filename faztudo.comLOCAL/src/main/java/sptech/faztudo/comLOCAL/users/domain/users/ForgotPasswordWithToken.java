package sptech.faztudo.comLOCAL.users.domain.users;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ForgotPasswordWithToken {
    @NotBlank
    private String password;
}
