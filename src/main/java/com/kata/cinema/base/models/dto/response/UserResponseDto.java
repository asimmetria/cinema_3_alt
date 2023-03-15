package com.kata.cinema.base.models.dto.response;

import lombok.Data;
import java.time.LocalDate;

@Data
public class UserResponseDto {
    public Long id;
    public String email;
    public String firstName;
    public String lastName;
    public String password;
    public LocalDate birthday;
    public UserResponseDto() {
    }

    public UserResponseDto(Long id, String email, String firstName, String lastName, String password, LocalDate birthday) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.birthday = birthday;
    }
}
