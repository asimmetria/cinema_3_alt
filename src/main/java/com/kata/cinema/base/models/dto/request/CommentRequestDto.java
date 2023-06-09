package com.kata.cinema.base.models.dto.request;

import lombok.Data;

@Data
public class CommentRequestDto {
    private String message;
    private Long parentId;
    private Integer level;

    public CommentRequestDto() {
    }

    public CommentRequestDto(String message, Long parentId, Integer level) {
        this.message = message;
        this.parentId = parentId;
        this.level = level;
    }
}
