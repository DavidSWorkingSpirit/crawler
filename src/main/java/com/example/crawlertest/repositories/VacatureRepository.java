package com.example.crawlertest.repositories;

import com.example.crawlertest.domein.Vacature;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VacatureRepository extends PagingAndSortingRepository<Vacature, Integer> {

    Optional<Vacature> findByUrl(String url);
    Page<Vacature> findAll(Pageable pageable);
    List<Vacature> findAll();

}
