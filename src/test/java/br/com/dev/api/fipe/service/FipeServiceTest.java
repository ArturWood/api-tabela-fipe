package br.com.dev.api.fipe.service;

import br.com.dev.api.fipe.domain.ResultDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

class FipeServiceTest {

    @Mock
    private FipeService fipeService;

    @Mock
    private RestTemplate restTemplate;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Deveria retornar lista de marcas")
    void scenario01() {
        // Arrange
        String tipo = "carros";
        String url = "https://parallelum.com.br/fipe/api/v1/carros/marcas";
        List<ResultDto> expectedResult = List.of(new ResultDto(
                "123",
                "FIAT"
        ));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ResponseEntity<List<ResultDto>> responseEntity = new ResponseEntity<>(expectedResult, HttpStatus.OK);

        when(restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<ResultDto>>() {
        }))
                .thenReturn(responseEntity);

        // Act
        List<ResultDto> result = fipeService.getMarcas(tipo);

        // Assert
        assertNotNull(result);
    }

    @Test
    @DisplayName("Deveria capturar erro 500 em request")
    void scenario02() {
        // Arrange
        String tipo = "carros";
        String url = "https://parallelum.com.br/fipe/api/v1/carros/marcas";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ResponseEntity<List<ResultDto>> responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        when(restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<ResultDto>>() {
        }))
                .thenReturn(responseEntity);

        // Act & Assert
        assertEquals(responseEntity.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
