package com.example.crawlertest.services;

import com.example.crawlertest.domein.Vacature;
import com.example.crawlertest.repositories.VacatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class VacatureService {

    private final Logger LOGGER = Logger.getLogger("VacatureServiceLog");
    private VacatureRepository vacatureRepository;

    @Autowired
    public VacatureService(VacatureRepository vacatureRepo) {
        this.vacatureRepository = vacatureRepo;
    }

    public boolean resultaatOpslaan(Vacature vacature) {
        if (!vacatureBestaatAl(vacature.getUrl())) {
            vacatureRepository.save(vacature);
            LOGGER.info("Nieuwe vacature gevonden: " + vacature.getTitel());
        }

        if (vacatureRepository.findByUrl(vacature.getUrl()).isPresent()) {
            return true;
        }

        return false;
    }

    public boolean vacatureBestaatAl(String url) {

        return vacatureRepository.findByUrl(url).isPresent();
    }
}
