package com.example.crawlertest.repositories;

import com.example.crawlertest.domein.Resultaat;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ResultaatRepository extends CrudRepository<Resultaat, Long> {

    List<Resultaat> findAll();
    Optional<Resultaat> findByUrl(String url);
}
