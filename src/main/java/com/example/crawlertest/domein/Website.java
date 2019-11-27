package com.example.crawlertest.domein;

import javax.persistence.*;

@Entity()
@Table(name = "WEBSITE")
public class Website {

    @Id
    @GeneratedValue(generator = "website_gen")
    @TableGenerator(name = "website_gen", table = "ama_sequence", pkColumnValue = "Website")
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAAM")
    private String naam;

    @Column(name = "URL", length = 1000)
    private String url;

    @Column(name = "filter")
    private String filter;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }
}
