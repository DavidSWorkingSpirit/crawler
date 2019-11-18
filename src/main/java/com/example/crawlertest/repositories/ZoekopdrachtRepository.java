package com.example.crawlertest.repositories;

import com.example.crawlertest.domein.Zoekopdracht;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZoekopdrachtRepository extends CrudRepository<Zoekopdracht, Long> {
}
