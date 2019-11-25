package com.example.crawlertest.services;

import com.example.crawlertest.domein.Resultaat;
import com.example.crawlertest.repositories.ResultaatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.logging.Logger;

@Service
public class ResultaatService {

    private final Logger LOGGER = Logger.getLogger("ResultaatServiceLog");

    @Autowired
    private ResultaatRepository resultaatRepository;

    public boolean resultaatOpslaan(Resultaat resultaat) {
        if (!resultaatBestaatAl(resultaat.getUrl())) {
            resultaatRepository.save(resultaat);
            LOGGER.info("Nieuw resultaat gevonden: " + resultaat.getTitel());
        }

        if (resultaatRepository.findByUrl(resultaat.getUrl()).isPresent()) {
            return true;
        }

        return false;
    }

    public boolean resultaatBestaatAl(String url) {
        Optional<Resultaat> resultaat = resultaatRepository.findByUrl(url);
        return resultaat.isPresent();
    }
}
