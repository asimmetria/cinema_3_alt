package com.kata.cinema.base.models.dto.request;

import com.kata.cinema.base.models.enums.TypeReview;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewRequestDto {
    private TypeReview typeReview;
    private String title;
    private String description;
}
