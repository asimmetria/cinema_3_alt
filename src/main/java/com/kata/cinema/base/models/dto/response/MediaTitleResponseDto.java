package com.kata.cinema.base.models.dto.response;

import com.kata.cinema.base.models.entitys.Media;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class MediaTitleResponseDto {

    Long id;
    String title;
    Long categoryId;
    Long countComments;
    String previewUrl;

    public MediaTitleResponseDto() {
    }

    public MediaTitleResponseDto(Long id, String title, Long categoryId, Long countComments, String previewUrl) {
        this.id = id;
        this.title = title;
        this.categoryId = categoryId;
        this.countComments = countComments;
        this.previewUrl = previewUrl;
    }
}
