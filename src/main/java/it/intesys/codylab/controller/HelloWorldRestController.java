package it.intesys.codylab.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldRestController {

    //private final FormeGeometricheService formeGeometriceService;

    //public HelloWorldRestController(FormeGeometricheService formeGeometriceService) {
    //    System.out.println("Creating HelloWorldRestController");
    //    this.formeGeometriceService = formeGeometriceService;
    //}

    @GetMapping("/hello")
    public String helloWorld() {
        //formeGeometriceService.stampaFormeGeometriche();
        return "Finito!";
    }
}
