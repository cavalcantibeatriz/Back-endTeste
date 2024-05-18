package sptech.faztudo.comLOCAL.users.domain.contractor;

import sptech.faztudo.comLOCAL.users.UserRole;

import java.time.LocalDate;

public record RegisterContractorDTO(String name,
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
                                    String descricao,
                                    byte[] image_profile,
                                    boolean proUser,
                                    UserRole role) {
    public static record UpdateContractor() {
    }
}
