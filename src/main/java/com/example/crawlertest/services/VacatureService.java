package com.example.crawlertest.services;

import com.example.crawlertest.domein.Vacature;
import com.example.crawlertest.domein.VacatureDTO;
import com.example.crawlertest.repositories.VacatureRepository;
import org.apache.commons.text.similarity.JaroWinklerDistance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.*;
import java.util.logging.Logger;

@Service
public class VacatureService {

    private final Logger LOGGER = Logger.getLogger(VacatureService.class.getName());
    private VacatureRepository vacatureRepository;

    @Autowired
    public VacatureService(VacatureRepository vacatureRepo) {this.vacatureRepository = vacatureRepo;}

    public boolean vacatureOpslaan(Vacature vacature) {
        if (!vacatureBestaatAl(vacature.getUrl())) {
            vacatureRepository.save(vacature);
            LOGGER.info("Nieuwe vacature gevonden: " + vacature.getTitel());
        }

        return vacatureRepository.findByUrl(vacature.getUrl()).isPresent();
    }

      private boolean vacatureBestaatAl(String url) {
        return vacatureRepository.findByUrl(url).isPresent();
    }

    public List<VacatureDTO> alleVacatures(int page, int size, String sortDir, String sort, List<String> zoekopdrachten){

        Pageable pageable = PageRequest.of(page, size, Sort.Direction.valueOf(sortDir), sort);
        Set<Vacature> vacatures = new HashSet<>();

        if (zoekopdrachten.size() == 0) {
            vacatures.addAll(vacatureRepository.findAllByTekst("", pageable).getContent());
        } else {
            vacatures.addAll(filterVacatures(zoekopdrachten, pageable).getContent());
        }

        List<VacatureDTO> vacatureLijst = new ArrayList<>();

        for (Vacature vacature : vacatures) {
                VacatureDTO tempVacatureDTO = new VacatureDTO();
                tempVacatureDTO.setUrl(vacature.getUrl());
                tempVacatureDTO.setTitel(vacature.getTitel());
                tempVacatureDTO.setId(vacature.getId());
                tempVacatureDTO.setDatum(vacature.getDatum());

                vacatureLijst.add(tempVacatureDTO);
        }
        return vacatureLijst;
    }

    public int aantalVacaturesOphalen (List<String> zoekopdrachten){
        Set<Vacature> vacatures = new HashSet<>();

        if (zoekopdrachten.size() == 0) {
            vacatures.addAll(vacatureRepository.findAllByTekst(""));
        } else {
            for (String zoekopdracht : zoekopdrachten) {
                String filteropdracht = "%"+zoekopdracht+"%";
                vacatures.addAll(vacatureRepository.findAllByTekst(filteropdracht));
            }
        }
        return vacatures.size();
    }

    public List<VacatureDTO> alleNieuweVacatures(int page, int size, String sortDir, String sort, List<String> zoekopdrachten){

        Pageable pageable = PageRequest.of(page, size, Sort.Direction.valueOf(sortDir), sort);
        Set<Vacature> vacatures = new HashSet<>();
        LocalDate datum = LocalDate.now();

        if (zoekopdrachten.size() == 0) {
            vacatures.addAll(vacatureRepository.findAllByDatum(datum, "", pageable).getContent());
        } else {
            vacatures.addAll(filterNieuweVacatures(zoekopdrachten, pageable).getContent());
        }

        List<VacatureDTO> lijstNieuweVacatures = new ArrayList<>();

        for (Vacature vacature : vacatures) {
            VacatureDTO tempVacatureDTO = new VacatureDTO();
            tempVacatureDTO.setUrl(vacature.getUrl());
            tempVacatureDTO.setTitel(vacature.getTitel());
            tempVacatureDTO.setId(vacature.getId());
            tempVacatureDTO.setDatum(vacature.getDatum());

            lijstNieuweVacatures.add(tempVacatureDTO);
        }
        return lijstNieuweVacatures;
    }

    public int aantalNieuweVacatures (List<String> zoekopdrachten){
        Set<Vacature> vacatures = new HashSet<>();
        LocalDate datum = LocalDate.now();

        if (zoekopdrachten.size() == 0) {
            vacatures.addAll(vacatureRepository.findAllByDatum(datum, ""));
        } else {
            for (String zoekopdracht : zoekopdrachten) {
                String filteropdracht = "%"+zoekopdracht+"%";
                vacatures.addAll(vacatureRepository.findAllByDatum(datum, filteropdracht));
            }
        }
        return vacatures.size();
    }

    private PageImpl<Vacature> filterVacatures(List<String> filters, Pageable pageable) {

        switch (filters.size()) {
            case 1: {
                return vacatureRepository.findAllByTekst(filters.get(0), pageable);
            }
            case 2: {
                return vacatureRepository.findAllByTekst(filters.get(0), filters.get(1), pageable);
            }
            case 3: {
                return vacatureRepository.findAllByTekst(filters.get(0), filters.get(1), filters.get(2), pageable);
            }
            case 4: {
                return vacatureRepository.findAllByTekst(filters.get(0), filters.get(1), filters.get(2),
                        filters.get(3), pageable);
            }
            case 5: {
                return vacatureRepository.findAllByTekst(filters.get(0), filters.get(1), filters.get(2),
                        filters.get(3), filters.get(4), pageable);
            }
            case 6: {
                return vacatureRepository.findAllByTekst(filters.get(0), filters.get(1), filters.get(2),
                        filters.get(3), filters.get(4), filters.get(5), pageable);
            }
        }
        return null;
    }

    private PageImpl<Vacature> filterNieuweVacatures(List<String> filters, Pageable pageable) {
        LocalDate datum = LocalDate.now();

        switch (filters.size()) {
            case 1: {
                return vacatureRepository.findAllByDatum(datum, filters.get(0), pageable);
            }
            case 2: {
                return vacatureRepository.findAllByDatum(datum, filters.get(0), filters.get(1), pageable);
            }
            case 3: {
                return vacatureRepository.findAllByDatum(datum, filters.get(0), filters.get(1),
                        filters.get(2), pageable);
            }
            case 4: {
                return vacatureRepository.findAllByDatum(datum, filters.get(0), filters.get(1),
                        filters.get(2), filters.get(3), pageable);
            }
            case 5: {
                return vacatureRepository.findAllByDatum(datum, filters.get(0), filters.get(1),
                        filters.get(2), filters.get(3), filters.get(4), pageable);
            }
            case 6: {
                return vacatureRepository.findAllByDatum(datum, filters.get(0), filters.get(1),
                        filters.get(2), filters.get(3), filters.get(4), filters.get(5), pageable);
            }
        }
        return null;
    }

    public Set<Vacature> verwijderDuplicaten() {
        LOGGER.info("Vergelijken van vacatures is gestart.");

        List<Vacature> vacatures = vacatureRepository.findAll();
        List<Vacature> checks = new ArrayList<>(vacatures);
        List<Vacature> trash = new ArrayList<>();

        Set<Vacature> gecheckteVacatures = new HashSet<>();
        Set<Vacature> duplicaten = new HashSet<>();

        vacatures.forEach(vacature1 -> {
            gecheckteVacatures.add(vacature1);
            checks.forEach(vacature2 -> {
                if (!gecheckteVacatures.contains(vacature2)) {
                    if (compareText(vacature1.getTekst(), vacature2.getTekst())) {
                        duplicaten.add(vacature2);
                        trash.add(vacature2);
                        LOGGER.info("Duplicaat gevonden: ID1 - " + vacature1.getId() + ", ID2 - " + vacature2.getId());
                    }
                }
            });
            checks.removeAll(trash);
            trash.clear();
        });

        // Verwijderen van alle dubbele vacatures
        vacatureRepository.deleteAll(duplicaten);

        LOGGER.info("Vergelijken van vacatures is beeïndigd.");
        LOGGER.info(duplicaten.size() + " duplicaten gevonden");
        return duplicaten;
    }

    public boolean compareText(String string1, String string2) {
        JaroWinklerDistance jwd = new JaroWinklerDistance();

        String shortest;
        String longest;

        if (string1.length() >= string2.length()) {
            longest = string1;
            shortest = string2;
        } else {
            shortest = string1;
            longest = string2;
        }

        double percentage = (double) shortest.length() / (double) longest.length() * 100.0;

        if (percentage > 80.0) {
            Double compare = jwd.apply(shortest, longest);
            return compare >= 0.9D && compare < 1.0D;
        }

        return false;
    }
}
