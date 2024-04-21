package br.com.dev.api.fipe.infra.exception;

public class ServerRequestException extends RuntimeException {
    public ServerRequestException(String message) {
        super(message);
    }
}
