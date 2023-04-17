package com.kata.cinema.base.webapp.facade.performance.impl;

import com.kata.cinema.base.models.dto.response.PerformanceResponseDto;
import com.kata.cinema.base.models.entitys.StudiosPerformance;
import com.kata.cinema.base.service.dto.StudioPerformanceDtoService;
import com.kata.cinema.base.service.entity.StudiosPerformanceService;
import com.kata.cinema.base.validation.StudiosPerformanceValidation;
import com.kata.cinema.base.webapp.facade.performance.StudiosPerformanceServiceFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudiosPerformanceServiceFacadeImpl implements StudiosPerformanceServiceFacade {

    private final StudiosPerformanceService performanceService;
    private final StudioPerformanceDtoService performanceDtoService;
    private final StudiosPerformanceValidation performanceValidation;

    @Override
    public List<PerformanceResponseDto> getPerformance() {
        return performanceDtoService.getPerformance();
    }

    @Override
    public void save(String name) {
        performanceService.save(name);
    }

    @Override
    public void update(Long id, String name) {
        performanceService.update(id, name);
    }

    @Override
    public void deleteById(Long id) {
        performanceValidation.isExistZeroPerformance(id);
        performanceService.deleteById(id);
    }
}
