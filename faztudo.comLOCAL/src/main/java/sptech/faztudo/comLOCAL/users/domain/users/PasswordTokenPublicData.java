package sptech.faztudo.comLOCAL.users.domain.users;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PasswordTokenPublicData {
    private final String email;
    private final Long createAtTimestamp;

}
