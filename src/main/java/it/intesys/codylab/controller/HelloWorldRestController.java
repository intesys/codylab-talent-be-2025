package it.intesys.codylab.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class HelloWorldRestController {
    @GetMapping("/hello")
    public String helloWorld() {
        return "Hello World";
    }
}
