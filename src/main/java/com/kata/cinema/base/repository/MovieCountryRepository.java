package com.kata.cinema.base.repository;

import com.kata.cinema.base.models.dto.response.CountryResponseDto;
import com.kata.cinema.base.models.entitys.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieCountryRepository extends JpaRepository<Country, Long> {
    @Query("SELECT new com.kata.cinema.base.models.dto.response.CountryResponseDto(" +
            "cr.id, cr.name) FROM Country cr JOIN cr.movie m WHERE m.id = :movieId")
    List<CountryResponseDto> getCountriesByMovieId(@Param("movieId") Long id);

    Country getCountryById(Long id);

    Country getCountryByName(String name);

    boolean existsCountryById(Long id);

    boolean existsCountryByName(String name);

    boolean existsCountryByMovieId(Long id);

}
