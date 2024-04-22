package br.com.dev.api.fipe.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ModelosResultDto(
        List<ResultDto> modelos
) {
}
