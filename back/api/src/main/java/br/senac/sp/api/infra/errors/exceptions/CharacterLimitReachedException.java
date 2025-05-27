package br.senac.sp.api.infra.errors.exceptions;

public class CharacterLimitReachedException extends RuntimeException {

    public CharacterLimitReachedException(String message) {
        super(message);
    }
}
