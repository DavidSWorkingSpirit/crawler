package com.example.crawlertest.services;

import com.example.crawlertest.domein.Resultaat;
import com.example.crawlertest.domein.Vacature;
import com.example.crawlertest.repositories.ResultaatRepository;
import com.example.crawlertest.repositories.VacatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VacatureService {

    private VacatureRepository vacatureRepository;
    private ResultaatRepository resultaatRepository;

    @Autowired
    public VacatureService(VacatureRepository vacatureRepo, ResultaatRepository resultaatRepo) {
        this.vacatureRepository = vacatureRepo;
        this.resultaatRepository = resultaatRepo;
    }

    public void maakVacatures() {
        List<Resultaat> resultaten = resultaatRepository.findAll();

        List<Vacature> vacatures = resultaten.stream().map(resultaat -> scanResultaat(resultaat)).collect(Collectors.toList());

        vacatureRepository.saveAll(vacatures);
    }

    public Vacature scanResultaat(Resultaat resultaat) {
        if (!vacatureRepository.findByUrl(resultaat.getUrl()).isPresent()) {
            Vacature vacature = new Vacature();
            vacature.setTitel(resultaat.getTitel());
            vacature.setUrl(resultaat.getUrl());

            System.out.println("Vacature wordt aangemaakt.");

            return vacature;
        }

        return null;
    }
}
