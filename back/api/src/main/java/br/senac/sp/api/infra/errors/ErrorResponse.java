package br.senac.sp.api.infra.errors;

public record ErrorResponse(String causa, String message) {
    public ErrorResponse(String causa, String message) {
        this.causa = causa;
        this.message = message;
    }
}
