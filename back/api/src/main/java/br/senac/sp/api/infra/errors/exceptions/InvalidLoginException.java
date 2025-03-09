package br.senac.sp.api.infra.errors.exceptions;

public class InvalidLoginException extends RuntimeException {
    public InvalidLoginException(String message) {
        super(message);
    }
}
