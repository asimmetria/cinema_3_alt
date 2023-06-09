package com.kata.cinema.base.models.dto.response;

import com.kata.cinema.base.models.entitys.StudiosPerformance;
import lombok.Data;

@Data
public class PerformanceResponseDto {

    private StudiosPerformance studiosPerformance;

    private Long id;
    private String name;

    public PerformanceResponseDto() {
    }

    public PerformanceResponseDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
