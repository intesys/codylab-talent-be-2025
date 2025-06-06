package it.intesys.codylab.Controllers;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/forme")
public class FormeController {

    private List<Forma> forme = new ArrayList<>();

    @GetMapping
    public ResponseEntity<List<Forma>> getAllForme() {
        return ResponseEntity.ok(forme);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Forma> getFormaById(@PathVariable Long id) {
        return forme.stream()
                .filter(forma -> forma.getId().equals(id))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Forma> createForma(@Valid @RequestBody Forma forma) {
        forme.add(forma);
        return ResponseEntity.status(HttpStatus.CREATED).body(forma);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Forma> updateForma(@PathVariable Long id, @Valid @RequestBody Forma formaAggiornata) {
        for (int i = 0; i < forme.size(); i++) {
            if (forme.get(i).getId().equals(id)) {
                formaAggiornata.setId(id);
                forme.set(i, formaAggiornata);
                return ResponseEntity.ok(formaAggiornata);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteForma(@PathVariable Long id) {
        boolean rimossa = forme.removeIf(forma -> forma.getId().equals(id));
        return rimossa ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
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

class Forma {
    @NotNull(message = "L'ID non può essere nullo")
    private Long id;

    @NotNull(message = "Il nome non può essere nullo")
    private String nome;

    private String descrizione;

    public Forma() {}

    public Forma(Long id, String nome, String descrizione) {
        this.id = id;
        this.nome = nome;
        this.descrizione = descrizione;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
}