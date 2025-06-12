package it.intesys.codylab.Controllers;

import it.intesys.codylab.dto.CredentialsDTO;
import it.intesys.codylab.repository.SqlFormaGeometricaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping("/api")
public class CredentialController {

    private final SqlFormaGeometricaRepository repo;

    @Autowired
    public CredentialController(SqlFormaGeometricaRepository repo) {
        this.repo = repo;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody CredentialsDTO credentials) {
        try {
            boolean accessoConsentito = repo.executePassCheckFE(
                    credentials.getId(), credentials.getPassword());

            if (accessoConsentito) {
                return ResponseEntity.ok("Accesso consentito");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Accesso negato");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Errore interno");
        }
    }
}
