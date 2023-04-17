package com.kata.cinema.base.service.dto;

import com.kata.cinema.base.models.dto.response.PerformanceResponseDto;

import java.util.List;

public interface StudioPerformanceDtoService {

    List<PerformanceResponseDto> getPerformance();
}
