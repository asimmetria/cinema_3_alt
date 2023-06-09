package com.kata.cinema.base.models.dto.response;

import lombok.Data;

@Data
public class UserCommentResponseDto {
    private Long id;
    private Long parentId;
    private Integer level;
    private String message;
    private UserNameResponseDto user;

    public UserCommentResponseDto() {
    }

    public UserCommentResponseDto(Long id, Long parentId, Integer level, String message) {
        this.id = id;
        this.parentId = parentId;
        this.level = level;
        this.message = message;
    }
}
