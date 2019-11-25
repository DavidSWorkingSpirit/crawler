package com.example.crawlertest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("secure")
public class SecureController {

    @GetMapping()
    public ResponseEntity<String> getSecuredTekst() {

        return ResponseEntity.ok("Secured tekst");
    }

    @GetMapping("admin")
    @Secured({"ADMIN"})
    public ResponseEntity<String> getSecuredAdmin() {

        return ResponseEntity.ok("Secured admin tekst");
    }
}
