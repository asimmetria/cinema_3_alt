package com.kata.cinema.base.models.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class UserNameResponseDto {
    private Long id;
    private String fullName;
    private String avatarUrl;
    @JsonIgnore
    private Long commentId;


    public UserNameResponseDto(Long commentId) {
        this.commentId = commentId;
    }

    public UserNameResponseDto(Long id, String fullName, String avatarUrl) {
        this.id = id;
        this.fullName = fullName;
        this.avatarUrl = avatarUrl;
    }
}
