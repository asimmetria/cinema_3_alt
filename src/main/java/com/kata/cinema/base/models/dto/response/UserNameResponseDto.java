package com.kata.cinema.base.models.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class UserNameResponseDto {
    public Long id;
    public String fullName;
    public String avatarUrl;
    @JsonIgnore
    public Long commentId;


    public UserNameResponseDto(Long commentId) {
        this.commentId = commentId;
    }

    public UserNameResponseDto(Long id, String fullName, String avatarUrl) {
        this.id = id;
        this.fullName = fullName;
        this.avatarUrl = avatarUrl;
    }
}
