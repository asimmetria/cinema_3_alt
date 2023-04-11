package com.kata.cinema.base.models.dto.request;

import com.kata.cinema.base.models.dto.response.CategoryResponseDto;
import lombok.Data;

@Data
public class MediaRequestDto {

    String title;
    String htmlBody;
    Long categoryId;

    public MediaRequestDto() {
    }

    public MediaRequestDto(String title, String htmlBody, Long categoryId) {
        this.title = title;
        this.htmlBody = htmlBody;
        this.categoryId = categoryId;
    }
}
