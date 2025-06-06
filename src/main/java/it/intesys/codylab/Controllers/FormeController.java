package it.intesys.codylab.Controllers;

import it.intesys.codylab.entities.Forma;

import it.intesys.codylab.repository.FormaRepository;
import jakarta.persistence.Entity;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/forme")
public class FormeController {

    @Autowired
    private FormaRepository formaRepository;
    //private List<Forma> forme = new ArrayList<>();

    @GetMapping
    public ResponseEntity<List<it.intesys.codylab.entities.Forma>> getAllForme() {
        return ResponseEntity.ok(formaRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<it.intesys.codylab.entities.Forma> getFormaById(@PathVariable Long id) {
        Optional<it.intesys.codylab.entities.Forma> forma = formaRepository.findById(id);
        return forma.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Forma> createForma(@Valid @RequestBody Forma forma) {
        Forma savedForma = formaRepository.save(forma);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedForma);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Forma> updateForma(@PathVariable Long id, @Valid @RequestBody Forma formaAggiornata) {
        return formaRepository.findById(id)
                .map(existingForma -> {
                    formaAggiornata.setId(id);
                    Forma updated = formaRepository.save(formaAggiornata);
                    return ResponseEntity.ok(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteForma(@PathVariable Long id) {
        if (formaRepository.existsById(id)) {
            formaRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGenericExceptions(Exception ex) {
        Map<String, String> error = new HashMap<>();
        error.put("messaggio", "Si è verificato un errore: " + ex.getMessage());
        return ResponseEntity.internalServerError().body(error);
    }
}