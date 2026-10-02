package io.github.ds.planeja.infra.handlers;

import io.github.ds.planeja.common.exceptions.RegistroNaoEncontratoException;
import io.github.ds.planeja.common.exceptions.ValidationException;
import io.github.ds.planeja.common.validation.CampoInvalido;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<?> handleValidationException(ValidationException ex) {
        var status = HttpStatus.UNPROCESSABLE_CONTENT;
        var body = Map.of(
                "timestamp", LocalDateTime.now(),
                "status", status.value(),
                "error", ex.getMessage(),
                "camposInvalidos", ex.getCampoInvalidos()
        );

        return new ResponseEntity<>(body, status);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationException(MethodArgumentNotValidException ex) {
        var camposInvalidos = ex.getFieldErrors().stream()
                .map(fe ->
                        new CampoInvalido(fe.getField(), fe.getDefaultMessage()))
                .toList();
        var status = HttpStatus.UNPROCESSABLE_CONTENT;
        var body = Map.of(
                "timestamp", LocalDateTime.now(),
                "status", status.value(),
                "error", ex.getMessage(),
                "camposInvalidos", camposInvalidos
        );

        return new ResponseEntity<>(body, status);
    }

    @ExceptionHandler(RegistroNaoEncontratoException.class)
    public ResponseEntity<?> handleRegistroNaoEncontratoException(RegistroNaoEncontratoException ex) {
        var status = HttpStatus.NOT_FOUND;

        var body = Map.of(
                "timestamp", LocalDateTime.now(),
                "status", status.value(),
                "error", ex.getMessage(),
                "message", ex.getMessage()
        );
        return new ResponseEntity<>(body, status);
    }
}
