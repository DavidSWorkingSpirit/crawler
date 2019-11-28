package com.example.crawlertest.controller;

import com.example.crawlertest.domein.Vacature;
import com.example.crawlertest.domein.VacatureDTO;
import com.example.crawlertest.services.VacatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "vacature")
public class VacatureController {

    private VacatureService vacatureService;

    @Autowired
    public VacatureController(VacatureService vacatureService){this.vacatureService = vacatureService;}

    @GetMapping("/")
    public ResponseEntity haalAlleVacaturesOp() {
        List<VacatureDTO> vacatureLijst = vacatureService.alleVacatures();
        return ResponseEntity.ok(vacatureLijst);
    }
}
