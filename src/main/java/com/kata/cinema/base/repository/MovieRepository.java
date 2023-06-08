package com.kata.cinema.base.repository;

import com.kata.cinema.base.models.dto.request.SearchMovieDto;
import com.kata.cinema.base.models.entitys.Movie;
import com.kata.cinema.base.models.enums.MPAA;
import com.kata.cinema.base.models.enums.RARS;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Repository;


import java.time.LocalDate;
import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long>, JpaSpecificationExecutor<Movie> {

    @EntityGraph(value = "movieGraph")
    Movie getMovieById(Long id);

    boolean existsMovieById(Long id);

    @EntityGraph(attributePaths = {"scores"})
    @Query("SELECT new com.kata.cinema.base.models.dto.request.SearchMovieDto"
        + "(m.id, m.name, m.originalName, m.previewUrl, m.dateRelease, "
        + "(SELECT AVG(s.score) FROM Score s WHERE s.movie = m)) "
        + "FROM Movie m WHERE m.name LIKE %:name%")
    List<SearchMovieDto> findByNameContaining(@Param("name") String name, Pageable pageable);

    @Query("SELECT m FROM Movie m WHERE (:name IS NULL OR m.name = :name) " +
            "AND (:startDate IS NULL OR m.dateRelease >= :startDate) " +
            "AND (:endDate IS NULL OR m.dateRelease <= :endDate) " +
            "AND (:genres IS NULL OR m.genre IN :genres) " +
            "AND (:rars IS NULL OR m.rars = :rars) " +
            "AND (:mpaa IS NULL OR m.mpaa = :mpaa)")
    Page<Movie> searchMovies(
            @Param("name") String name,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("genres") List<String> genres,
            @Param("rars") RARS rars,
            @Param("mpaa") MPAA mpaa,
            Pageable pageable
    );


}
