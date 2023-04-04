package com.kata.cinema.base.models.dto.response;

import lombok.Data;

@Data
public class UserNameResponseDto {
    public Long id;
    public String fullName;
    public String avatarUrl;

    public UserNameResponseDto(Long id) {
        this.id = id;
    }

    public UserNameResponseDto(Long id, String fullName, String avatarUrl) {
        this.id = id;
        this.fullName = fullName;
        this.avatarUrl = avatarUrl;
    }
}
