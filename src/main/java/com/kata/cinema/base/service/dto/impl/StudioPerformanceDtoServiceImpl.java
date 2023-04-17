package com.kata.cinema.base.service.dto.impl;

import com.kata.cinema.base.models.dto.response.PerformanceResponseDto;
import com.kata.cinema.base.repository.StudiosPerformanceRepository;
import com.kata.cinema.base.service.dto.StudioPerformanceDtoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudioPerformanceDtoServiceImpl implements StudioPerformanceDtoService {

    private final StudiosPerformanceRepository performanceRepository;

    @Override
    public List<PerformanceResponseDto> getPerformance() {
        return performanceRepository.getPerformance();
    }
}
