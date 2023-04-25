package com.kata.cinema.base.repository;

import com.kata.cinema.base.models.dto.response.PerformanceResponseDto;
import com.kata.cinema.base.models.entitys.StudiosPerformance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudiosPerformanceRepository extends JpaRepository<StudiosPerformance, Long> {

    @Query("SELECT p FROM StudiosPerformance p ")
    List<PerformanceResponseDto> getAll();

    @Query("SELECT COUNT(p.id) > 0 FROM StudiosPerformance p JOIN p.movies m WHERE m.id=: movieId")
    boolean existsStudiosPerformanceByZero(@Param("movieId") Long id);
}
