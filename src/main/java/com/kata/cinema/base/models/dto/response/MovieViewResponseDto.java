package com.kata.cinema.base.models.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kata.cinema.base.models.enums.MPAA;
import com.kata.cinema.base.models.enums.RARS;
import com.kata.cinema.base.models.enums.TypeMedia;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieViewResponseDto {
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String originalName;
    private Integer time;
    private TypeMedia type;

    //TODO рефакт стран
    //private List<CountryResponseDto> countries;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    private LocalDate dateRelease;
    private RARS rars;
    private MPAA mpaa;
    private String description;
    private String previewUrl;
    private List<GenreResponseDto> genres;
    private Double score;
    private Long countScore;

    //TODO пока игнорировать, заполнить потом
    //private Integer myScore;

    private List<CastResponseDto> casts;
}
