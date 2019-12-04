package com.example.crawlertest.repositories;

import com.example.crawlertest.domein.Vacature;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface VacatureRepository extends PagingAndSortingRepository<Vacature, Integer> {

    Optional<Vacature> findByUrl(String url);
    @Query(value = "SELECT * FROM vacature e WHERE e.datum LIKE %?1%",
        nativeQuery = true)
    List<Vacature> findAllByDatum(LocalDate datum);

    @Query(value = "SELECT * FROM vacature e WHERE e.datum LIKE %?1%",
            nativeQuery = true)
    Page<Vacature> findAllByDatum(LocalDate datum, Pageable pageable);

    @Query(value = "SELECT * FROM vacature e WHERE e.tekst LIKE %?1%",
    nativeQuery = true)
    Page<Vacature> findAllByTekst(String zoekopdracht, Pageable pageable);

    @Query(value = "SELECT * FROM vacature e WHERE e.tekst LIKE %?1%",
    nativeQuery = true)
    List<Vacature> findAllByTekst(String zoekopdracht);

}
