package br.senac.sp.api.infra.errors.exceptions;

public class InvalidOrExpiredTokenExpection extends RuntimeException{

    public InvalidOrExpiredTokenExpection(String message) {
        super(message);
    }
}
