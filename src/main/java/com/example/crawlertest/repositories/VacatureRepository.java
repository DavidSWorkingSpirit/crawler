package com.example.crawlertest.repositories;

import com.example.crawlertest.domein.Vacature;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface VacatureRepository extends PagingAndSortingRepository<Vacature, Integer> {

    Optional<Vacature> findByUrl(String url);
    @Query(value = "SELECT * FROM vacature e WHERE e.datum LIKE %:datum% AND e.tekst LIKE %:zoekOpdracht",
        nativeQuery = true)
    List<Vacature> findAllByDatum(@Param("datum") LocalDate datum, @Param("zoekOpdracht") String zoekOpdracht);

    @Query(value = "SELECT * FROM vacature e WHERE e.datum LIKE %:datum% AND e.tekst LIKE %:zoekOpdracht%",
            nativeQuery = true)
    PageImpl<Vacature> findAllByDatum(@Param("datum") LocalDate datum, @Param("zoekOpdracht") String zoekOpdracht, Pageable pageable);

    @Query(value = "SELECT * FROM vacature e WHERE e.tekst LIKE %?1%",
    nativeQuery = true)
    PageImpl<Vacature> findAllByTekst(String zoekopdracht, Pageable pageable);

    @Query(value = "SELECT * FROM vacature e WHERE e.tekst LIKE %?1%",
    nativeQuery = true)
    List<Vacature> findAllByTekst(String zoekopdracht);

}
