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
    public void save(String name) {
        StudiosPerformance performance = new StudiosPerformance();
        performance.setName(name);
        performanceRepository.save(performance);
    }

    @Override
    public void update(Long id, String name) {
        StudiosPerformance performance = performanceRepository.getReferenceById(id);
        performance.setName(name);
        performanceRepository.save(performance);
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
