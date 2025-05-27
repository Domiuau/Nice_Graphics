package br.senac.sp.api.infra.errors.exceptions;

public class IACommunicationErrorException extends RuntimeException {

    public IACommunicationErrorException(String message) {
        super(message);
    }
}
