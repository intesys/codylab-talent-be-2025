package it.intesys.codylab.controller;

import it.intesys.codylab.business.FormeGeometricheService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class FormeGeometricheRestController {

    private final FormeGeometricheService formeGeometricheService;

    public FormeGeometricheRestController(FormeGeometricheService formeGeometricheService) {
        this.formeGeometricheService = formeGeometricheService;
    }
    @GetMapping("/stampa-forme")
    public String stampaForme() {
        formeGeometricheService.stampaFormeGeometriche();
        return "Finito!";
    }
}
