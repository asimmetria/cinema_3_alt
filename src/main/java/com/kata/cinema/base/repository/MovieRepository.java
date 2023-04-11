package com.kata.cinema.base.repository;

import com.kata.cinema.base.models.entitys.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
