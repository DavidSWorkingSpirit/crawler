package com.example.crawlertest.domein;

import com.google.i18n.phonenumbers.Phonenumber;

import javax.persistence.*;

@Entity()
@Table(name = "Vacature")
public class Vacature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "TITEL")
    private String titel;

    @Column(name = "URL", unique = true)
    private String url;

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
}
