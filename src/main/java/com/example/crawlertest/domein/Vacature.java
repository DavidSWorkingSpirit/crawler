package com.example.crawlertest.domein;

import javax.persistence.*;

@Entity()
@Table(name = "Vacature")
public class Vacature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "TITEL", length = 2000)
    private String titel;

    @Column(name = "TEKST", length = 100000)
    private String tekst;

    @Column(name = "URL", unique = true)
    private String url;

    @ManyToOne
    private Zoekopdracht zoekopdracht;

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

    public String getTekst() {
        return tekst;
    }

    public void setTekst(String tekst) {
        this.tekst = tekst;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Zoekopdracht getZoekopdracht() {
        return zoekopdracht;
    }

    public void setZoekopdracht(Zoekopdracht zoekopdracht) {
        this.zoekopdracht = zoekopdracht;
    }
}
