package com.kata.cinema.base.models.dto.response;

import lombok.Data;

@Data
public class UserCommentResponseDto {
    public Long id;
    public Long parentId;
    public Integer level;
    public String message;
    public UserNameResponseDto user;

    public UserCommentResponseDto() {
    }

    public UserCommentResponseDto(Long id, Long parentId, Integer level, String message) {
        this.id = id;
        this.parentId = parentId;
        this.level = level;
        this.message = message;
    }
}
