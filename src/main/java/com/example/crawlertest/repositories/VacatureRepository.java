package com.example.crawlertest.repositories;

import com.example.crawlertest.domein.Vacature;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface VacatureRepository extends PagingAndSortingRepository<Vacature, Integer> {

    List<Vacature> findAll();

    Optional<Vacature> findByUrl(String url);
    @Query(value = "SELECT * FROM vacature e WHERE e.datum LIKE %:datum% AND e.tekst LIKE %:zoekOpdracht",
            nativeQuery = true)
    List<Vacature> findAllByDatum(@Param("datum") LocalDate datum, @Param("zoekOpdracht") String zoekOpdracht);

    @Query(value = "SELECT * FROM vacature e WHERE e.datum LIKE %:datum% AND e.tekst LIKE %:zoekOpdracht%",
            nativeQuery = true)
    PageImpl<Vacature> findAllByDatum(@Param("datum") LocalDate datum, @Param("zoekOpdracht") String zoekOpdracht, Pageable pageable);

    @Query(value = "SELECT * FROM vacature e WHERE e.tekst LIKE %?1%", nativeQuery = true)
    PageImpl<Vacature> findAllByTekst(String zoekopdracht, Pageable pageable);

    @Query(value = "SELECT * FROM vacature e WHERE e.tekst LIKE %?1%", nativeQuery = true)
    List<Vacature> findAllByTekst(String zoekopdracht);

    @Query(value = "SELECT * FROM vacature e WHERE e.tekst LIKE %:filter1% OR e.tekst LIKE %:filter2%", nativeQuery = true)
    PageImpl<Vacature> findAllByTekst(@Param("filter1") String filter1, @Param("filter2") String filter2, Pageable pageable);

    @Query(value = "SELECT * FROM vacature e WHERE e.tekst LIKE %:filter1% OR e.tekst LIKE %:filter2% " +
            "OR e.tekst LIKE %:filter3%", nativeQuery = true)
    PageImpl<Vacature> findAllByTekst(@Param("filter1") String filter1, @Param("filter2") String filter2,
                                      @Param("filter3") String filter3, Pageable pageable);

    @Query(value = "SELECT * FROM vacature e WHERE e.tekst LIKE %:filter1% OR e.tekst LIKE %:filter2% " +
            "OR e.tekst LIKE %:filter3% OR e.tekst LIKE %:filter4%", nativeQuery = true)
    PageImpl<Vacature> findAllByTekst(@Param("filter1") String filter1, @Param("filter2") String filter2,
                                      @Param("filter3") String filter3, @Param("filter4") String filter4, Pageable pageable);

    @Query(value = "SELECT * FROM vacature e WHERE e.tekst LIKE %:filter1% OR e.tekst LIKE %:filter2% " +
            "OR e.tekst LIKE %:filter3% OR e.tekst LIKE %:filter4% OR e.tekst LIKE %:filter5%", nativeQuery = true)
    PageImpl<Vacature> findAllByTekst(@Param("filter1") String filter1, @Param("filter2") String filter2,
                                      @Param("filter3") String filter3, @Param("filter4") String filter4,
                                      @Param("filter5") String filter5, Pageable pageable);

    @Query(value = "SELECT * FROM vacature e WHERE e.tekst LIKE %:filter1% OR e.tekst LIKE %:filter2% " +
            "OR e.tekst LIKE %:filter3% OR e.tekst LIKE %:filter4% OR e.tekst LIKE %:filter5% OR e.tekst LIKE %:filter6%",
            nativeQuery = true)
    PageImpl<Vacature> findAllByTekst(@Param("filter1") String filter1, @Param("filter2") String filter2,
                                      @Param("filter3") String filter3, @Param("filter4") String filter4,
                                      @Param("filter5") String filter5, @Param("filter6") String filter6, Pageable pageable);

    @Query(value = "SELECT * FROM vacature e WHERE e.datum LIKE %:datum% AND e.tekst LIKE %:filter1% " +
            "OR e.tekst LIKE %:filter2%", nativeQuery = true)
    PageImpl<Vacature> findAllByDatum(@Param("datum") LocalDate datum, @Param("filter1") String filter1,
                                      @Param("filter2") String filter2, Pageable pageable);

    @Query(value = "SELECT * FROM vacature e WHERE e.datum LIKE %:datum% AND e.tekst LIKE %:filter1% " +
            "OR e.tekst LIKE %:filter2% OR e.tekst LIKE %:filter3%", nativeQuery = true)
    PageImpl<Vacature> findAllByDatum(@Param("datum") LocalDate datum, @Param("filter1") String filter1,
                                      @Param("filter2") String filter2, @Param("filter3") String filter3, Pageable pageable);

    @Query(value = "SELECT * FROM vacature e WHERE e.datum LIKE %:datum% AND e.tekst LIKE %:filter1% " +
            "OR e.tekst LIKE %:filter2% OR e.tekst LIKE %:filter3% OR e.tekst LIKE %:filter4%", nativeQuery = true)
    PageImpl<Vacature> findAllByDatum(@Param("datum") LocalDate datum, @Param("filter1") String filter1,
                                      @Param("filter2") String filter2, @Param("filter3") String filter3,
                                      @Param("filter4") String filter4, Pageable pageable);

    @Query(value = "SELECT * FROM vacature e WHERE e.datum LIKE %:datum% AND e.tekst LIKE %:filter1% " +
            "OR e.tekst LIKE %:filter2% OR e.tekst LIKE %:filter3% OR e.tekst LIKE %:filter4% " +
            "OR e.tekst LIKE %:filter5%", nativeQuery = true)
    PageImpl<Vacature> findAllByDatum(@Param("datum") LocalDate datum, @Param("filter1") String filter1,
                                      @Param("filter2") String filter2, @Param("filter3") String filter3,
                                      @Param("filter4") String filter4, @Param("filter5") String filter5, Pageable pageable);

    @Query(value = "SELECT * FROM vacature e WHERE e.datum LIKE %:datum% AND e.tekst LIKE %:filter1% " +
            "OR e.tekst LIKE %:filter2% OR e.tekst LIKE %:filter3% OR e.tekst LIKE %:filter4% " +
            "OR e.tekst LIKE %:filter5% OR e.tekst LIKE %:filter6%", nativeQuery = true)
    PageImpl<Vacature> findAllByDatum(@Param("datum") LocalDate datum, @Param("filter1") String filter1,
                                      @Param("filter2") String filter2, @Param("filter3") String filter3,
                                      @Param("filter4") String filter4, @Param("filter5") String filter5,
                                      @Param("filter6") String filter6, Pageable pageable);
}
