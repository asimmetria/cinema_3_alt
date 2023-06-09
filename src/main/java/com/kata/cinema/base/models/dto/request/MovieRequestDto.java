package com.kata.cinema.base.models.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kata.cinema.base.models.enums.MPAA;
import com.kata.cinema.base.models.enums.RARS;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
public class MovieRequestDto {

    @NotBlank
    private String name;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    private LocalDate dateRelease;

    @NotBlank
    private RARS rars;

    @NotBlank
    private MPAA mpaa;

    @NotBlank
    private int time;

    private String description;

    @NotBlank
    private String originalName;

    private List<Long> genreIds;

    private List<Long> countryIds;

    public MovieRequestDto() {
    }
}
