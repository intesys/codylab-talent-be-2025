package it.intesys.codylab.controller;

import it.intesys.codylab.api.model.ProblemApiDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // You can define exception handling methods here
    // For example, to handle specific exceptions globally

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ProblemApiDTO> handleJsonParseException(HttpMessageNotReadableException ex) {
        ProblemApiDTO problem = new ProblemApiDTO();
        problem.setDetail(ex.getMessage());
        problem.setTimestamp(OffsetDateTime.now());
        problem.setStatus(HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problem);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ProblemApiDTO> handleValidationException(MethodArgumentNotValidException ex) {
        ProblemApiDTO problem = new ProblemApiDTO();
        problem.setDetail("Errore di validazione: " + ex.getMessage());
        problem.setTimestamp(OffsetDateTime.now());
        problem.setStatus(HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problem);
    }

//    @ExceptionHandler(NoHandlerFoundException.class)
//    public ResponseEntity<ProblemApiDTO> handleNoHandlerFoundException(NoHandlerFoundException ex) {
//        ProblemApiDTO problem = new ProblemApiDTO();
//        problem.setDetail("La risorsa richiesta non è stata trovata: " + ex.getRequestURL());
//        problem.setTimestamp(OffsetDateTime.now());
//        problem.setStatus(HttpStatus.NOT_FOUND.value());
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(problem);
//
//    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ProblemApiDTO> handleGenericException(Exception ex) {
        ProblemApiDTO problem = new ProblemApiDTO();
        problem.setDetail("Si è verificato un errore interno: " + ex.getMessage());
        problem.setTimestamp(OffsetDateTime.now());
        problem.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(problem);
    }
}