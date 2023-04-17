package com.kata.cinema.base.service.entity;

import com.kata.cinema.base.models.entitys.StudiosPerformance;

import java.util.List;

public interface StudiosPerformanceService {

    void save(String name);

    void update(Long id, String name);

    void deleteById(Long id);

    StudiosPerformance getPerformanceById(Long id);

}
