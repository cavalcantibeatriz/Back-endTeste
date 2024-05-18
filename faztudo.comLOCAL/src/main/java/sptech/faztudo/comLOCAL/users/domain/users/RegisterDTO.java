package sptech.faztudo.comLOCAL.users.domain.users;

import sptech.faztudo.comLOCAL.users.UserRole;

import java.time.LocalDate;

public record RegisterDTO(String name,
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
                          LocalDate dt_cadastro,
                          String descricao,
                          byte[] image_profile,
                          UserRole role) {
}
