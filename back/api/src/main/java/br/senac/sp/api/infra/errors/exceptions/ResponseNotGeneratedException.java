package br.senac.sp.api.infra.errors.exceptions;

public class ResponseNotGeneratedException extends RuntimeException {

    public ResponseNotGeneratedException(String message) {
        super(message);
    }
}
