package br.com.dev.api.fipe.service;

import br.com.dev.api.fipe.domain.ResultDto;
import br.com.dev.api.fipe.infra.exception.ServerRequestException;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class FipeService {
    private final String ENDERECO_BASE = "https://parallelum.com.br/fipe/api/v1/";
    private final RestTemplate restTemplate = new RestTemplate();

    public List<ResultDto> getMarcas(String tipo) {
        String url = ENDERECO_BASE + tipo + "/marcas";
        ResponseEntity<List<ResultDto>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<ResultDto>>() {}
        );
        isRequestOk(response.getStatusCode());
        return response.getBody();
    }

    private void isRequestOk(HttpStatusCode statusCode) {
        if (!statusCode.is2xxSuccessful())
            throw new ServerRequestException("Request Error");
    }
}
