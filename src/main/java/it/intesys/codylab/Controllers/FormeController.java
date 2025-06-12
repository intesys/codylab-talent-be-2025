package it.intesys.codylab.Controllers;

import it.intesys.codylab.entities.Forma;
import it.intesys.codylab.repository.FormaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/forme")
public class FormeController {

    @Autowired
    private FormaRepository formaRepository;

    @GetMapping
    public ResponseEntity<List<Forma>> getAllForme() {
        return ResponseEntity.ok(formaRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Forma> getFormaById(@PathVariable Long id) {
        Optional<Forma> forma = formaRepository.findById(id);
        return forma.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Forma> createForma(@Valid @RequestBody Forma forma) {
        Forma savedForma = formaRepository.save(forma);
        return ResponseEntity.status(201).body(savedForma);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Forma> updateForma(@PathVariable Long id, @Valid @RequestBody Forma formaAggiornata) {
        return formaRepository.findById(id)
                .map(existingForma -> {
                    formaAggiornata.setId(id);
                    Forma updated = formaRepository.save(formaAggiornata);
                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteForma(@PathVariable Long id) {
        if (formaRepository.existsById(id)) {
            formaRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
