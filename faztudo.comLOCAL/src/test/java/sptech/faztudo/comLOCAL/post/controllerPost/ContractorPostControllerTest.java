package sptech.faztudo.comLOCAL.post.controllerPost;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import sptech.faztudo.comLOCAL.post.domainPost.upload.ContractorPost;
import sptech.faztudo.comLOCAL.post.repositoryPost.ContractorPostRepository;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@AutoConfigureMockMvc
class ContractorPostControllerTest {

    @Mock
    private ContractorPostRepository contractorPostRepository;
    @InjectMocks
    ContractorPostController contractorPostController;


    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void criarContractorPost() {

        ContractorPost contractorPost = new ContractorPost();

        var dateTime = LocalDateTime.now();
        contractorPost.setDataCriacao(dateTime);

        Integer id = 1;

        when(contractorPostRepository.save(contractorPost)).thenReturn(contractorPost);

        var response = contractorPostController.criarContractorPost(contractorPost, id);
        verify(contractorPostRepository, times(1)).save(any(ContractorPost.class));

        assertEquals(201, response.getStatusCode().value());
        assertEquals(contractorPost, response.getBody());

    }

    @Test
    void obterContractorPost() {

        Long id = 10L;
        Integer id2 = 10;
        ContractorPost contractorPost = new ContractorPost();


        when(contractorPostRepository.findAllByFkContractor(id2)).thenReturn(List.of(contractorPost));

        ResponseEntity<List<ContractorPost>> response = contractorPostController.obterContractorPost(id2);

        verify(contractorPostRepository).findAllByFkContractor(id2);

        assertEquals(200, response.getStatusCode().value());
    }


// ...

    @Test
    void atualizarContractorPost() {

        ContractorPost contractorPost = new ContractorPost();
        Long id = 10L;

        when(contractorPostRepository.findById(id)).thenReturn(Optional.of(contractorPost));

        Map<String, Object> updates = new HashMap<>();
        updates.put("descricao", "Nova descrição");
        updates.put("foto", "12345");
        updates.put("categoria", "1");

        ResponseEntity<ContractorPost> response = contractorPostController.atualizarContractorPost(id, updates);


        when(contractorPostRepository.save(contractorPost)).thenReturn(contractorPost);


        verify(contractorPostRepository).findById(id);
        verify(contractorPostRepository, times(1)).save(any(ContractorPost.class));
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    void excluirContractorPost() {
        Long id = 10L;

        when(contractorPostRepository.findById(id)).thenReturn(Optional.of(new ContractorPost()));
        ResponseEntity<Void> response = contractorPostController.excluirContractorPost(id);

        verify(contractorPostRepository).findById(id);
        verify(contractorPostRepository).delete(any(ContractorPost.class));
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }


}

