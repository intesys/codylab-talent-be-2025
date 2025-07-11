package it.intesys.codylab.controller;

import it.intesys.codylab.api.model.ApiErrorApiDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.OffsetDateTime;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiErrorApiDTO> handleJsonParseException(HttpMessageNotReadableException e) {
        ApiErrorApiDTO error = new ApiErrorApiDTO()
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Bad Request")
                .message("Invalid or malformed JSON, please check the payload")
                .details(e.getMostSpecificCause() != null ? e.getMostSpecificCause().getMessage() : e.getMessage())
                .timestamp(OffsetDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorApiDTO> handleValidationException(MethodArgumentNotValidException e) {
        ApiErrorApiDTO error = new ApiErrorApiDTO()
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Bad Request")
                .message("Validation error")
                .details(e.getBindingResult().toString())
                .timestamp(OffsetDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler({EntityNotFoundException.class, NoSuchElementException.class})
    public ResponseEntity<ApiErrorApiDTO> handleNotFoundException(Exception e) {
        ApiErrorApiDTO error = new ApiErrorApiDTO()
                .status(HttpStatus.NOT_FOUND.value())
                .error("Not Found")
                .message(e.getMessage() != null ? e.getMessage() : "Resource not found")
                .details(e.getMessage())
                .timestamp(OffsetDateTime.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorApiDTO> handleGenericException(Exception e) {
        ApiErrorApiDTO error = new ApiErrorApiDTO()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .error("Internal Server Error")
                .message("An internal error occurred")
                .details(e.getMessage())
                .timestamp(OffsetDateTime.now());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}
