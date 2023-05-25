package com.kata.cinema.base.repository;

import com.kata.cinema.base.models.entitys.TopMovie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopMovieRepository extends JpaRepository<TopMovie, Long> {


}
