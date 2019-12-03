package com.example.crawlertest.domein;

import java.sql.Timestamp;

public class VacatureDTO {

    private Long id;
    private String titel;
    private String url;
    private Long aantalVactures;
    private Timestamp datum;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getAantalVactures() { return aantalVactures; }

    public void setAantalVactures(Long aantalVactures) { this.aantalVactures = aantalVactures; }

    public Timestamp getDatum() { return datum; }

    public void setDatum(Timestamp datum) { this.datum = datum; }
}
