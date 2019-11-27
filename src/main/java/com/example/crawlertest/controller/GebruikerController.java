package com.example.crawlertest.controller;

import com.example.crawlertest.domein.Gebruiker;
import com.example.crawlertest.services.GebruikerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "gebruiker")
public class GebruikerController {

    private GebruikerService gebruikerService;

    @Autowired
    public GebruikerController(GebruikerService gebruikerService) {
        this.gebruikerService = gebruikerService;
    }

    @GetMapping("/")
    public List<Gebruiker> geefAlleGebruikers() {
        return gebruikerService.geefAlleGebruikers();
    }

    @PostMapping("/")
    public ResponseEntity gebruikerOpslaan(@RequestBody Gebruiker gebruiker) {

        if (gebruikerService.gebruikerOpslaan(gebruiker)) {
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.badRequest().build();
    }
}
