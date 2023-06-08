package com.kata.cinema.base.repository;

import com.kata.cinema.base.models.entitys.Collection;
import com.kata.cinema.base.models.entitys.CollectionMovie;
import com.kata.cinema.base.models.entitys.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CollectionMovieRepository extends JpaRepository<CollectionMovie, Long> {
    List<CollectionMovie> findByCollectionAndMovieIn(Collection collection, List<Movie> movies);

}
