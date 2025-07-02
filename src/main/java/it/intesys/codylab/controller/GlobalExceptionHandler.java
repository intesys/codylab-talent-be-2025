package it.intesys.codylab.controller;

import it.intesys.codylab.api.model.ApiErrorApiDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiErrorApiDTO> handleJsonParseException(HttpMessageNotReadableException e) {
        ApiErrorApiDTO error = new ApiErrorApiDTO()
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Bad Request")
                .message("JSON non valido o malformato")
                .details(e.getMessage())
                .timestamp(OffsetDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler({ EntityNotFoundException.class, NoSuchElementException.class })
    public ResponseEntity<ApiErrorApiDTO> handleNotFoundException(Exception e) {
        ApiErrorApiDTO error = new ApiErrorApiDTO()
                .status(HttpStatus.NOT_FOUND.value())
                .error("Not Found")
                .message("Risorsa non trovata")
                .details(e.getMessage())
                .timestamp(OffsetDateTime.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorApiDTO> handleGenericException(Exception e) {
        ApiErrorApiDTO error = new ApiErrorApiDTO()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .error("Internal Server Error")
                .message("AHAHAHAHAHAHHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAH")
                .details(e.getMessage())
                .timestamp(OffsetDateTime.now());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}
