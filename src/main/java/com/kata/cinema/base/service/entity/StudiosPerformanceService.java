package com.kata.cinema.base.service.entity;

import com.kata.cinema.base.models.entitys.StudiosPerformance;

import java.util.List;

public interface StudiosPerformanceService {

    void save(StudiosPerformance performance);

    void update(StudiosPerformance updatedPerformance);

    void deleteById(Long id);

    StudiosPerformance getPerformanceById(Long id);

}
