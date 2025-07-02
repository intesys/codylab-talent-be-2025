package it.intesys.codylab.controller;

import it.intesys.codylab.api.model.ProblemApiDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    // You can define exception handling methods here
    // For example, to handle specific exceptions globally

     @ExceptionHandler(Exception.class)
     public ResponseEntity<ProblemApiDTO> handleSomeException(Exception ex) {
         System.err.println("An error occurred: " + ex.getMessage());
         ProblemApiDTO problem = new ProblemApiDTO();
         problem.setDetail(ex.getMessage());
         problem.setTimestamp(OffsetDateTime.now());
         problem.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(problem);
     }

    // Add more exception handlers as needed

}
