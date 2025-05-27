package br.senac.sp.api.infra.errors.exceptions;

public class InvalidOrExpiredTokenException extends RuntimeException{

    public InvalidOrExpiredTokenException(String message) {
        super(message);
    }
}
