package com.kata.cinema.base.my;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TopMovieRepository extends JpaRepository<TopMovie, Long> {


    @Query("SELECT tm FROM TopMovie tm WHERE tm.movie.numRatings > 5000 ORDER BY tm.positionRating ASC")
    List<TopMovie> findTop250Movies();
}

