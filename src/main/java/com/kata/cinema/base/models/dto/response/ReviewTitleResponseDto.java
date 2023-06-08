package com.kata.cinema.base.models.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewTitleResponseDto {
    private Long id;
    private String title;
    private LocalDate date;
    private Long movieId;
    private String name;
    private String originalName;

}
