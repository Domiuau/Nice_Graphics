package br.senac.sp.api.infra.errors;

import br.senac.sp.api.infra.errors.exceptions.*;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InvalidOrExpiredTokenException.class)
    private ResponseEntity<?> handleInvalidOrExpiredTokenExpection(InvalidOrExpiredTokenException ex) {
        return ResponseEntity.status(401).body(new ErrorResponse(ex.getMessage(), "Faça login novamente para continuar"));
    }

    @ExceptionHandler(ActionNotAllowedException.class)
    private ResponseEntity<?> handleActionNotAllowedException(ActionNotAllowedException ex) {
        return ResponseEntity.status(403).body(new ErrorResponse(ex.getMessage(), "Ação não permitida"));
    }

    @ExceptionHandler(CharacterLimitReachedException.class)
    private ResponseEntity<?> handleCharacterLimitReachedException(CharacterLimitReachedException ex) {
        return ResponseEntity.status(400).body(new ErrorResponse("Limite de caracteres atingido", ex.getMessage()));
    }

    @ExceptionHandler(InvalidLoginException.class)
    private ResponseEntity<?> handleInvalidLoginException(InvalidLoginException ex) {
        return ResponseEntity.status(401).body(new ErrorResponse(ex.getMessage(), "Verifique seu login e senha e tenta novamente"));
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity handleSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException ex) {
        var erros = ex.getMessage().split("'");


        return ResponseEntity.badRequest().body(new ErrorResponse(erros[3].split("\\.")[1], erros[1] + " já está em uso."));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException ex) {
        var erros = ex.getMessage().split("'")[1].split("/");
        System.out.println(ex.getMessage());
        return ResponseEntity.badRequest().body(new ErrorResponse(erros[0], erros[1]));
    }

    @ExceptionHandler(ResponseNotGeneratedException.class)
    private ResponseEntity<?> handleResponseNotGeneratedException(ResponseNotGeneratedException ex) {
        return ResponseEntity.status(400).body(new ErrorResponse(ex.getMessage(), "Se o problema persistir, tente trocar de modelo ou usar uma IA diferente."));
    }

    @ExceptionHandler(IACommunicationErrorException.class)
    private ResponseEntity<?> handleIACommunicationErrorException(IACommunicationErrorException ex) {
        return ResponseEntity.status(400).body(new ErrorResponse(ex.getMessage(), "Se o problema persistir, verifique sua conexão com a internet ou tente usar uma IA diferente."));
    }





    private record ErrorResponse(String causa, String message) {
        private ErrorResponse(String causa, String message) {
            this.causa = causa;
            this.message = message;
        }
    }

}
