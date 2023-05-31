package com.kata.cinema.base.models.dto.request;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchMovieDto {

    Long id;
    String name;
    String originalName;
    String previewUrl;
    LocalDate date;
    Integer avgScore;

}
