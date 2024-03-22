package edu.cintha.chantier.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    // Requête de type GET
    @GetMapping("/")
    public String hello() {
        return "<h1>Le serveur marche mais il n'y a rien à voir ici</h1>";
    }

}
