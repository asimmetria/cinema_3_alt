package com.kata.cinema.base.my;

import com.kata.cinema.base.models.entitys.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
    List<Rating> findByMovie(Movie movie);
    int countByMovie(Movie movie);
}

