package br.com.dev.api.fipe.controller;

import br.com.dev.api.fipe.domain.ResultDto;
import br.com.dev.api.fipe.domain.ValoresResultDto;
import br.com.dev.api.fipe.service.FipeService;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tabela-fipe")
public class FipeController {
    @Autowired
    private FipeService fipeService;

    @GetMapping("/{tipo}")
    public ResponseEntity<List<ResultDto>> getMarcas(@PathVariable @Pattern(regexp = "(?i)CARROS|MOTOS|CAMINHOES") String tipo) {
        List<ResultDto> marcas = fipeService.getMarcas(tipo);
        return ResponseEntity.ok(marcas);
    }

    @GetMapping("/{tipo}/{marcaId}")
    public ResponseEntity<List<ResultDto>> getModelos(@PathVariable @Pattern(regexp = "(?i)CARROS|MOTOS|CAMINHOES") String tipo,
                                                      @PathVariable @Pattern(regexp = "\\d{1,3}") String marcaId) {
        List<ResultDto> modelos = fipeService.getModelos(tipo, marcaId);
        return ResponseEntity.ok(modelos);
    }

    @GetMapping("/{tipo}/{marcaId}/{modeloId}")
    public ResponseEntity<List<ValoresResultDto>> getValores(@PathVariable @Pattern(regexp = "(?i)CARROS|MOTOS|CAMINHOES") String tipo,
                                                             @PathVariable @Pattern(regexp = "\\d{1,3}") String marcaId,
                                                             @PathVariable @Pattern(regexp = "\\d{4,5}") String modeloId) {
        List<ValoresResultDto> valores = fipeService.getValores(tipo, marcaId, modeloId);
        return ResponseEntity.ok(valores);
    }
}
