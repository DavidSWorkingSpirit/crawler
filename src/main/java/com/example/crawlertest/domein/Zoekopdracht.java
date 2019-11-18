package com.example.crawlertest.domein;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity()
@Table(name = "ZOEKOPDRACHT")
public class Zoekopdracht {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "WEBSITE")
    private String website;

    @Column(name = "ZOEKTERM")
    private String zoekterm;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "zoekopdracht")
    @JsonIgnoreProperties({"zoekopdracht"})
    private List<Resultaat> resultaten;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getZoekterm() {
        return zoekterm;
    }

    public void setZoekterm(String zoekterm) {
        this.zoekterm = zoekterm;
    }

    public List<Resultaat> getResultaten() {
        return resultaten;
    }

    public void setResultaten(List<Resultaat> resultaten) {
        this.resultaten = resultaten;
    }
}
