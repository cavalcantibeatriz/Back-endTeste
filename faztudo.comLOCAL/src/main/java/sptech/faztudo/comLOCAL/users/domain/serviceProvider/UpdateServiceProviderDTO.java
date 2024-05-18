package sptech.faztudo.comLOCAL.users.domain.serviceProvider;

public record UpdateServiceProviderDTO(String cep,
                                       String logradouro,
                                       String state,
                                       String city,
                                       String phone,
                                       Category category) {
}
