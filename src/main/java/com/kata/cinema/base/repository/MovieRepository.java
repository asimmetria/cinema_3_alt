package com.kata.cinema.base.repository;

import com.kata.cinema.base.models.entitys.Movie;
import com.kata.cinema.base.models.enums.MPAA;
import com.kata.cinema.base.models.enums.RARS;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long>, JpaSpecificationExecutor<Movie> {

    @EntityGraph(value = "movieGraph")
    Movie getMovieById(Long id);

    boolean existsMovieById(Long id);

    Page<Movie> searchMovies (
            String name,
            LocalDate startDate,
            LocalDate endDate,
            List<String> genres,
            RARS rars,
            MPAA mpaa,
            Pageable pageable
    );
}
