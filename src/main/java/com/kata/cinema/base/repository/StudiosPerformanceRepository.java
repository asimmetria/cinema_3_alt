package com.kata.cinema.base.repository;

import com.kata.cinema.base.models.dto.response.PerformanceResponseDto;
import com.kata.cinema.base.models.entitys.StudiosPerformance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface StudiosPerformanceRepository extends JpaRepository<StudiosPerformance, Long> {

    public List<PerformanceResponseDto> getPerformance();
}
