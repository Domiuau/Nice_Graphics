package br.senac.sp.api.infra.errors;

import br.senac.sp.api.infra.errors.exceptions.ActionNotAllowedException;
import br.senac.sp.api.infra.errors.exceptions.CharacterLimitReachedException;
import br.senac.sp.api.infra.errors.exceptions.InvalidLoginException;
import br.senac.sp.api.infra.errors.exceptions.InvalidOrExpiredTokenExpection;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InvalidOrExpiredTokenExpection.class)
    private ResponseEntity<?> handleInvalidOrExpiredTokenExpection(InvalidOrExpiredTokenExpection ex) {
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

    private record ErrorResponse(String causa, String message) {
        private ErrorResponse(String causa, String message) {
            this.causa = causa;
            this.message = message;
        }
    }

}
