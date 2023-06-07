package com.kata.cinema.base.models.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieResponseDto {

    private Long id;
    private String name;
    private String originalName;
    private int time;
    private LocalDate dateRelease;
    private String previewUrl;
    private List<String> countries;
    private List<String> genres;
    private Double avgScore;
    private Long countScore;


    public MovieResponseDto(
            Long id,
            String name,
            String originalName,
            int time,
            LocalDate dateRelease,
            String previewUrl,
            Double avgScore,
            Long countScore
    ) {
        this.id = id;
        this.name = name;
        this.originalName = originalName;
        this.time = time;
        this.dateRelease = dateRelease;
        this.previewUrl = previewUrl;
        this.avgScore = avgScore;
        this.countScore = countScore;
    }


}
