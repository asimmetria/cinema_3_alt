package com.kata.cinema.base.repository;

import com.kata.cinema.base.models.dto.response.MovieResponseDto;
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

import java.time.LocalDate;
import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long>, JpaSpecificationExecutor<Movie> {

    @EntityGraph(value = "movieGraph")
    Movie getMovieById(Long id);

    boolean existsMovieById(Long id);

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

    @Query("select distinct new com.kata.cinema.base.models.dto.response.MovieResponseDto(" +

            "m.id, " +
            "m.name, " +
            "m.originalName, " +
            "m.time, " +
            "m.dateRelease, " +
            "m.previewUrl," +
            "(select avg(s.score) from Score s where s.movie = m)," +
            "(select count(s.score) from Score s where s.movie = m)" +

            ") " +

            "from Movie m join CollectionMovie cm on cm.movie.id = m.id " +
            " join m.country c join m.genre g " +
            "where (:genreId is null or g.id = :genreId) " +
            "and (:countryId is null or c.id = :countryId) " +
            "and cm.collection.id = :id " +
            "and ( cast(:rDate as date ) is null or  m.dateRelease = cast(:rDate as date )) ")
    List<MovieResponseDto> getMoviesResponseByCollection(@Param("id") Long id,
                                                         @Param("rDate") LocalDate dataRel,
                                                         @Param("countryId") Long countryId,
                                                         @Param("genreId") Long genreId);

}