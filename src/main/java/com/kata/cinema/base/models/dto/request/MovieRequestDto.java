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
    public String name;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    public LocalDate dateRelease;

    @NotBlank
    public RARS rars;

    @NotBlank
    public MPAA mpaa;

    @NotBlank
    public int time;

    public String description;

    @NotBlank
    public String originalName;

    List<Long> genreIds;

    //TODO добавить после рефакта стран
    //List<Long> countryIds;

    public MovieRequestDto() {
    }
}
