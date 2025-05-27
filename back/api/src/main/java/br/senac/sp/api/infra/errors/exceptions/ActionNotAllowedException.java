package br.senac.sp.api.infra.errors.exceptions;

public class ActionNotAllowedException extends RuntimeException{

    public ActionNotAllowedException(String message) {
        super(message);
    }
}