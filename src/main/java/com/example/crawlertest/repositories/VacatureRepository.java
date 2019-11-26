package com.example.crawlertest.repositories;

import com.example.crawlertest.domein.Vacature;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VacatureRepository extends CrudRepository<Vacature, Long> {

    List<Vacature> findAll();
    Optional<Vacature> findByUrl(String url);
}
