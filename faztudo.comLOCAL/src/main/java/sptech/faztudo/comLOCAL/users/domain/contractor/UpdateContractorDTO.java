package sptech.faztudo.comLOCAL.users.domain.contractor;

public record UpdateContractorDTO(String cep,
                                  String logradouro,
                                  String state,
                                  String city,
                                  String phone) {
}
