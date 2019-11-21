package com.example.crawlertest.domein;

import javax.persistence.*;

@Entity()
@Table(name = "VACATURELIJST")
public class VacatureLijst {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

}