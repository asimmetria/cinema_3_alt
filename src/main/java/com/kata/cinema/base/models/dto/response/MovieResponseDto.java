package com.kata.cinema.base.models.dto.response;

import lombok.*;

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
    //  @Formula("select avg(s.score) from Score s where s.movie.id = id")
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
