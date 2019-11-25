package com.example.crawlertest.services;

import com.example.crawlertest.domein.Resultaat;
import com.example.crawlertest.repositories.ResultaatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ResultaatService {

    @Autowired
    private ResultaatRepository resultaatRepository;

    public boolean resultaatBestaatAl(String url) {
        Optional<Resultaat> resultaat = resultaatRepository.findByUrl(url);
        return resultaat.isPresent();
    }
}
