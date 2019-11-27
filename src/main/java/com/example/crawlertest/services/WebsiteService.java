package com.example.crawlertest.services;

import com.example.crawlertest.domein.Website;
import com.example.crawlertest.repositories.WebsiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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

    public Optional<Website> geefWebsiteOpId(Long id) {

        return websiteRepository.findById(id);
    }

    public boolean wijzigWebsite(Long id, Website website) {
        Optional<Website> teWijzigenWebsite = websiteRepository.findById(id);

        if (teWijzigenWebsite.isPresent() && !isGelijk(teWijzigenWebsite.get(), website)) {
            websiteRepository.save(website);
            return true;
        }

        return false;
    }

    private boolean isGelijk(Website bestaand, Website gewijzigd) {
        boolean gelijkeNaam = bestaand.getNaam().equals(gewijzigd.getNaam());
        boolean gelijkeUrl = bestaand.getUrl().equals(gewijzigd.getUrl());
        boolean gelijkFilter = bestaand.getFilter().equals(gewijzigd.getFilter());

        if (gelijkeNaam && gelijkeUrl && gelijkFilter) {
            return true;
        }

        return false;
    }
}
