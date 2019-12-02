package com.example.crawlertest.domein;

public class VacatureDTO {

    private Long id;
    private String titel;
    private String url;
    private Long aantalVactures;

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
}
