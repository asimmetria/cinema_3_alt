package com.kata.cinema.base.webapp.facade.performance.impl;

import com.kata.cinema.base.models.dto.response.PerformanceResponseDto;
import com.kata.cinema.base.models.entitys.StudiosPerformance;
import com.kata.cinema.base.service.dto.StudioPerformanceDtoService;
import com.kata.cinema.base.service.entity.StudiosPerformanceService;
import com.kata.cinema.base.webapp.facade.performance.StudiosPerformanceServiceFacade;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class StudiosPerformanceServiceFacadeImpl implements StudiosPerformanceServiceFacade {

    private final StudiosPerformanceService performanceService;
    private final StudioPerformanceDtoService performanceDtoService;

    @Override
    public List<PerformanceResponseDto> getPerformance() {
        return performanceDtoService.getPerformance();
    }

    @Override
    public void save(String name) {
        StudiosPerformance performance = new StudiosPerformance();
        performance.setName(name);
        performanceService.save(performance);
    }

    @Override
    public void update(Long id, String name) {
        StudiosPerformance performance = performanceService.getPerformanceById(id);
        performance.setName(name);
        performanceService.save(performance);
    }

    @Override
    public void deleteById(Long id) {
        performanceService.deleteById(id);
    }
}
