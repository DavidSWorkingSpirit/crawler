package com.example.crawlertest.controller;

import com.example.crawlertest.domein.SorteerDTO;
import com.example.crawlertest.domein.VacatureDTO;
import com.example.crawlertest.services.VacatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.Time;
import java.sql.Timestamp;
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
                sorteerDTO.getSize(), sorteerDTO.getSortDir(), sorteerDTO.getSort(), sorteerDTO.getZoekopdracht());

        return ResponseEntity.ok(vacatureLijst);
    }

    @PostMapping("/zoekopdracht")
    public ResponseEntity krijgAantalVacatures(@RequestBody SorteerDTO sorteerDTO){
        return ResponseEntity.ok(vacatureService.aantalVacaturesOphalen(sorteerDTO.getZoekopdracht()));
    }

    @PostMapping("/nieuweVacatures")
    public ResponseEntity haalAlleNieuweVacatures(@RequestBody SorteerDTO sorteerDTO){
        return ResponseEntity.ok(vacatureService.alleNieuweVacatures(sorteerDTO.getPage(), sorteerDTO.getSize(),
                sorteerDTO.getSortDir(), sorteerDTO.getSort()));
    }

    @PostMapping("/datum")
    public ResponseEntity aantalNieuweVacatures(@RequestBody SorteerDTO sorteerDTO){
        return ResponseEntity.ok(vacatureService.aantalNieuweVacatures());
    }
}
