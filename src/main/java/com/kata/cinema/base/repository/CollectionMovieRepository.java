package com.kata.cinema.base.repository;

import com.kata.cinema.base.models.entitys.Collection;
import com.kata.cinema.base.models.entitys.CollectionMovie;
import com.kata.cinema.base.models.entitys.Movie;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CollectionMovieRepository extends JpaRepository<CollectionMovie, Long> {
    List<CollectionMovie> findByCollectionAndMovieIn(Collection collection, List<Movie> movies);

}
