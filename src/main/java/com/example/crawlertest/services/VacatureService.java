package com.example.crawlertest.services;

import com.example.crawlertest.domein.Vacature;
import com.example.crawlertest.domein.VacatureDTO;
import com.example.crawlertest.repositories.VacatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@Service
public class VacatureService {

    private final Logger LOGGER = Logger.getLogger("VacatureServiceLog");
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

    public List<VacatureDTO> alleVacatures(int page, int size, String sortDir, String sort, String zoekopdracht){

        Pageable pageable = PageRequest.of(page, size, Sort.Direction.valueOf(sortDir), sort);
        String filterOpdracht = "%"+zoekopdracht+"%";
        List<Vacature> vacatures = (vacatureRepository.findAllByTekst(filterOpdracht, pageable)).getContent();
        List<VacatureDTO> vacatureLijst = new ArrayList<>();
        System.out.println(vacatures.get(1).getDatum());

        for (Vacature vacature:vacatures) {
                VacatureDTO tempVacatureDTO = new VacatureDTO();
                tempVacatureDTO.setUrl(vacature.getUrl());
                tempVacatureDTO.setTitel(vacature.getTitel());
                tempVacatureDTO.setId(vacature.getId());
                tempVacatureDTO.setDatum(vacature.getDatum());

                vacatureLijst.add(tempVacatureDTO);
        }
        return vacatureLijst;
    }

    public int aantalVacaturesOphalen (String zoekopdracht){
        String filteropdracht = "%"+zoekopdracht+"%";
        return (vacatureRepository.findAllByTekst(zoekopdracht)).size();
    }

    public List<VacatureDTO> alleNieuweVacatures(int page, int size, String sortDir, String sort){

        Pageable pageable = PageRequest.of(page, size, Sort.Direction.valueOf(sortDir), sort);
        LocalDate datum = LocalDate.now();
        List<Vacature> vacatures = (vacatureRepository.findAllByDatum(datum, pageable)).getContent();
        List<VacatureDTO> lijstNieuweVacatures = new ArrayList<>();
        System.out.println(vacatures.size());
        System.out.println("het is nu" + datum);

        for (Vacature vacature:vacatures) {
            VacatureDTO tempVacatureDTO = new VacatureDTO();
            tempVacatureDTO.setUrl(vacature.getUrl());
            tempVacatureDTO.setTitel(vacature.getTitel());
            tempVacatureDTO.setId(vacature.getId());
            tempVacatureDTO.setDatum(vacature.getDatum());

            lijstNieuweVacatures.add(tempVacatureDTO);
        }
        return lijstNieuweVacatures;
    }

    public int aantalNieuweVacatures (){
        LocalDate datum = LocalDate.now();
        return (vacatureRepository.findAllByDatum(datum)).size();
    }
}
