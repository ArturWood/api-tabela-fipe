package br.com.dev.api.fipe.service;

import br.com.dev.api.fipe.domain.ModelosResultDto;
import br.com.dev.api.fipe.domain.ResultDto;
import br.com.dev.api.fipe.domain.ValoresResultDto;
import br.com.dev.api.fipe.infra.exception.ServerRequestException;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Service
public class FipeService {
    private final String ENDERECO_BASE = "https://parallelum.com.br/fipe/api/v1/";
    private final RestTemplate restTemplate = new RestTemplate();

    public List<ResultDto> getMarcas(String tipo) {
        String url = ENDERECO_BASE + tipo + "/marcas";
        ResponseEntity<List<ResultDto>> response = this.getResulList(url);
        this.isRequestOk(response.getStatusCode());
        return this.getSortedResultList(Objects.requireNonNull(response.getBody()));
    }

    public List<ResultDto> getModelos(String tipo, String marcaId) {
        String url = ENDERECO_BASE + tipo + "/marcas/" + marcaId + "/modelos";
        ResponseEntity<ModelosResultDto> response = restTemplate.getForEntity(url, ModelosResultDto.class);
        this.isRequestOk(response.getStatusCode());
        return this.getSortedResultList(Objects.requireNonNull(response.getBody()).modelos());
    }

    public List<ValoresResultDto> getValores(String tipo, String marcaId, String modeloId) {
        String url = ENDERECO_BASE + tipo + "/marcas/" + marcaId + "/modelos/" + modeloId + "/anos/";
        ResponseEntity<List<ResultDto>> response = this.getResulList(url);
        this.isRequestOk(response.getStatusCode());
        List<ResultDto> sortedResultList = this.getSortedResultList(Objects.requireNonNull(response.getBody()));
        return this.getValoresDto(url, sortedResultList);
    }

    private List<ValoresResultDto> getValoresDto(String url, List<ResultDto> sortedResultList) {
        List<ValoresResultDto> valoresResultDtoList = new ArrayList<>();
        for (int i = 0; i < sortedResultList.size(); i++) {
            String request = url + sortedResultList.get(i).codigo();
            ResponseEntity<ValoresResultDto> response = restTemplate.getForEntity(request, ValoresResultDto.class);
            this.isRequestOk(response.getStatusCode());
            valoresResultDtoList.add(response.getBody());
        }
        return valoresResultDtoList;
    }

    private ResponseEntity<List<ResultDto>> getResulList(String url) {
        return restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<ResultDto>>() {}
        );
    }

    private List<ResultDto> getSortedResultList(List<ResultDto> modelos) {
        return modelos.stream()
                .sorted(Comparator.comparing(ResultDto::nome))
                .toList();
    }

    private void isRequestOk(HttpStatusCode statusCode) {
        if (!statusCode.is2xxSuccessful())
            throw new ServerRequestException("Request Error");
    }
}
