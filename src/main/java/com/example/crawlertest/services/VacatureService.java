package com.example.crawlertest.services;

import com.example.crawlertest.domein.Vacature;
import com.example.crawlertest.domein.VacatureDTO;
import com.example.crawlertest.repositories.VacatureRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
public class VacatureService {

    private final Logger LOGGER = Logger.getLogger("VacatureServiceLog");
    private VacatureRepository vacatureRepository;
    public ModelMapper modelMapper;


    @Autowired
    public VacatureService(VacatureRepository vacatureRepo) {this.vacatureRepository = vacatureRepo;}




    public boolean vacatureOpslaan(Vacature vacature) {
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

    public List<VacatureDTO> alleVacatures(int page, int size, String sortDir, String sort){

        Pageable pageable = PageRequest.of(page, size);
        List<Vacature> vacatures = (vacatureRepository.findAll(pageable)).getContent();
        System.out.println(vacatureRepository.findAll(pageable));
        System.out.println(vacatures.get(0).getTitel());
        List<VacatureDTO> vacatureLijst = new ArrayList<>();

        for (Vacature element:vacatures) {
            VacatureDTO tempVacatureDTO = new VacatureDTO();
            tempVacatureDTO.setUrl(element.getUrl());
            tempVacatureDTO.setTitel(element.getTitel());
            tempVacatureDTO.setId(element.getId());

            vacatureLijst.add(tempVacatureDTO);
        }
        return vacatureLijst;
    }
}
