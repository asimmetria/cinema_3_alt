package com.kata.cinema.base.webapp.facade.admin;

import com.kata.cinema.base.models.dto.response.PerformanceResponseDto;

import java.util.List;

public interface StudiosPerformanceServiceFacade {

    List<PerformanceResponseDto> getPerformance();

    void save(String name);

    void update(Long id, String name);

    void deleteById(Long id);
}
