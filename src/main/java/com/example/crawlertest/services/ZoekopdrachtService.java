package com.example.crawlertest.services;

import com.example.crawlertest.domein.Zoekopdracht;
import com.example.crawlertest.repositories.ZoekopdrachtRepository;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.util.logging.Logger;

@Service
public class ZoekopdrachtService {

    private ZoekopdrachtRepository zoekopdrachtRepo;

    @Autowired
    public ZoekopdrachtService(ZoekopdrachtRepository zoekopdrachtRepository) {
        this.zoekopdrachtRepo = zoekopdrachtRepository;
    }

    public void zoekopdrachtOpslaan(Zoekopdracht zoekopdracht) {

        zoekopdrachtRepo.save(zoekopdracht);
    }
}
