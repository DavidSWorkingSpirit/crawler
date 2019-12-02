package com.example.crawlertest.controller;

import com.example.crawlertest.domein.SorteerDTO;
import com.example.crawlertest.domein.VacatureDTO;
import com.example.crawlertest.services.VacatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "vacature")
public class VacatureController {

    private VacatureService vacatureService;

    @Autowired
    public VacatureController(VacatureService vacatureService){this.vacatureService = vacatureService;}

    @PostMapping("/")
    public ResponseEntity haalAlleVacaturesOp(@RequestBody SorteerDTO sorteerDTO) {
        List<VacatureDTO> vacatureLijst = vacatureService.alleVacatures(sorteerDTO.getPage(),
                sorteerDTO.getSize(), sorteerDTO.getSortDir(), sorteerDTO.getSort());

        return ResponseEntity.ok(vacatureLijst);
    }

    @GetMapping("/")
    public ResponseEntity krijgAantalVacatures(){
        long aantal = vacatureService.aantalVacaturesOphalen();
        Long aantalVacatures = aantal;
        return ResponseEntity.ok(aantalVacatures);
    }
}
