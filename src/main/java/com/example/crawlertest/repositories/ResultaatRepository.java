package com.example.crawlertest.repositories;

import com.example.crawlertest.domein.Resultaat;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultaatRepository extends CrudRepository<Resultaat, Long> {
}
