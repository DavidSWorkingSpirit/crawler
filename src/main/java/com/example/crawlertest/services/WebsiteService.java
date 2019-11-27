package com.example.crawlertest.services;

import com.example.crawlertest.domein.Website;
import com.example.crawlertest.repositories.WebsiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WebsiteService {

    private WebsiteRepository websiteRepository;

    @Autowired
    public WebsiteService(WebsiteRepository websiteRepo) {
        this.websiteRepository = websiteRepo;
    }

    public boolean websiteOpslaan(Website website) {
        websiteRepository.save(website);

        if (websiteRepository.findById(website.getId()).isPresent()) {
            return true;
        }

        return false;
    }

    public List<Website> geefAlleWebsites() {

        return websiteRepository.findAll();
    }
}
