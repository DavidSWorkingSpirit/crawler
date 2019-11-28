package com.example.crawlertest.services;

import com.example.crawlertest.domein.Zoekterm;
import com.example.crawlertest.repositories.ZoektermRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZoektermService {

    private ZoektermRepository zoektermRepository;

    @Autowired
    public ZoektermService(ZoektermRepository zoektermRepo) {
        this.zoektermRepository = zoektermRepo;
    }

    public List<Zoekterm> geefAlleZoektermen() {

        return zoektermRepository.findAll();
    }

    public boolean zoektermOpslaan(Zoekterm zoekterm) {
        zoektermRepository.save(zoekterm);

        if (zoektermRepository.findById(zoekterm.getId()).isPresent()) {
            return true;
        }

        return false;
    }
}
