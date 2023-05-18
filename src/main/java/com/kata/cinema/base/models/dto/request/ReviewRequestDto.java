package com.kata.cinema.base.models.dto.request;

import com.kata.cinema.base.models.enums.TypeReview;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewRequestDto {
    private TypeReview typeReview;
    private String title;
    private String description;
    private Long userId;
    private LocalDate date;
}
