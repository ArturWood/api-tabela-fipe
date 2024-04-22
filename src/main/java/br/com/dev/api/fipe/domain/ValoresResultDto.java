package br.com.dev.api.fipe.domain;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ValoresResultDto(
        @JsonAlias("Valor")
        String valor,
        @JsonAlias("Modelo")
        String modelo,
        @JsonAlias("AnoModelo")
        Integer anoModelo,
        @JsonAlias("Combustivel")
        String tipoCombustivel
) {
}
