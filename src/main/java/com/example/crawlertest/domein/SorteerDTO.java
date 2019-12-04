package com.example.crawlertest.domein;

import java.sql.Timestamp;

public class SorteerDTO {

    private int page;
    private int size;
    private String sortDir;
    private String sort;
    private String zoekopdracht;
    private Timestamp datum;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getSortDir() {
        return sortDir;
    }

    public void setSortDir(String sortDir) {
        this.sortDir = sortDir;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getZoekopdracht() { return zoekopdracht; }

    public void setZoekopdracht(String zoekopdracht) { this.zoekopdracht = zoekopdracht; }

    public Timestamp getDatum() { return datum;  }

    public void setDatum(Timestamp datum) { this.datum = datum; }
}
