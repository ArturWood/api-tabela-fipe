package br.com.dev.api.fipe.infra.exception;

import br.com.dev.api.fipe.infra.exception.dto.ErrorResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

@ControllerAdvice
public class ExceptionEntityHandler {
    @ExceptionHandler(ServerRequestException.class)
    public ResponseEntity handlerServerRequest() {
        return ResponseEntity.internalServerError().build();
    }

    @ExceptionHandler(HandlerMethodValidationException.class)
    public ResponseEntity<ErrorResponseDto> handlerMethodValidation() {
        return ResponseEntity.badRequest().body(new ErrorResponseDto("Formato inválido, obrigatorio enviar carros|motos|caminhoes"));
    }
}
