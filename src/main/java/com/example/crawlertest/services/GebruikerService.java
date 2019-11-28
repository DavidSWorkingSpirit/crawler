package com.example.crawlertest.services;

import com.example.crawlertest.domein.Gebruiker;
import com.example.crawlertest.repositories.GebruikerRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GebruikerService {

    private GebruikerRepository gebruikerRepository;
    private PasswordEncoder encoder;

    @Autowired
    public GebruikerService(GebruikerRepository gebruikerRepo, PasswordEncoder passwordEncoder) {
        this.gebruikerRepository = gebruikerRepo;
        this.encoder = passwordEncoder;
    }

    public List<Gebruiker> geefAlleGebruikers() {

        return gebruikerRepository.findAll();
    }

    public boolean gebruikerOpslaan(Gebruiker gebruiker) {
        String randomWachtwoord = RandomStringUtils.randomAlphabetic(10);

        if (!gebruikerRepository.findByGebruikersnaam(gebruiker.getVoornaam().substring(0,1) +
                                                               gebruiker.getAchternaam()).isPresent()) {
            gebruiker.setWachtwoord(encoder.encode(randomWachtwoord));
            gebruiker.setGebruikersnaam(gebruiker.getVoornaam().substring(0,1) + gebruiker.getAchternaam());
            gebruikerRepository.save(gebruiker);
        } else {
            gebruiker.setWachtwoord(encoder.encode(randomWachtwoord));
            gebruiker.setGebruikersnaam(gebruiker.getVoornaam().substring(0,1) +
                                        gebruiker.getAchternaam() +
                                        RandomStringUtils.randomNumeric(5));
            gebruikerRepository.save(gebruiker);
        }

        return gebruikerRepository.findById(gebruiker.getId()).isPresent();
    }

    public Optional<Gebruiker> geefGebruikerOpId(Long id) {

        return gebruikerRepository.findById(id);
    }

    public boolean gebruikerWijzigen(Long id, Gebruiker gebruiker) {
        Optional<Gebruiker> teWijzigenGebruiker = gebruikerRepository.findById(id);

        if (teWijzigenGebruiker.isPresent()) {
            gebruikerRepository.save(gebruiker);
            return true;
        }

        return false;
    }

    public boolean gebruikerVerwijderen(Long id) {
        Optional<Gebruiker> teVerwijderenGebruiker = gebruikerRepository.findById(id);

        if (teVerwijderenGebruiker.isPresent()) {
            gebruikerRepository.delete(teVerwijderenGebruiker.get());

            return !gebruikerRepository.findById(id).isPresent();
        }

        return false;
    }
}
