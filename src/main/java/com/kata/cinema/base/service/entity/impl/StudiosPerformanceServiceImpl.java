package com.kata.cinema.base.service.entity.impl;

import com.kata.cinema.base.models.entitys.StudiosPerformance;
import com.kata.cinema.base.repository.StudiosPerformanceRepository;
import com.kata.cinema.base.service.entity.StudiosPerformanceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StudiosPerformanceServiceImpl implements StudiosPerformanceService {

    private final StudiosPerformanceRepository performanceRepository;

    @Override
    public void save(StudiosPerformance performance) {
        performanceRepository.save(performance);
    }

    @Override
    public void update(StudiosPerformance updatedPerformance) {
        performanceRepository.save(updatedPerformance);
    }

    @Override
    public void deleteById(Long id) {
        performanceRepository.deleteById(id);
    }

    @Override
    public StudiosPerformance getPerformanceById(Long id) {
        return performanceRepository.getReferenceById(id);
    }
}
