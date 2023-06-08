package com.kata.cinema.base.models.dto.request;

import com.kata.cinema.base.models.enums.TypeReview;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReviewRequestDto {
    private TypeReview typeReview;
    private String title;
    private String description;
    private LocalDate date;


    public ReviewRequestDto(TypeReview typeReview, String title, String description) {
        this.typeReview = typeReview;
        this.title = title;
        this.description = description;
    }

    public ReviewRequestDto(TypeReview typeReview, String title, String description, LocalDate date) {
        this.typeReview = typeReview;
        this.title = title;
        this.description = description;
        this.date = date;
    }
}
