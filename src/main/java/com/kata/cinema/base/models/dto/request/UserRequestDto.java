package com.kata.cinema.base.models.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserRequestDto {
    @NotBlank
    private String email;
    @NotBlank
    private String firstName;
    private String lastName;
    @NotBlank
    private String password;
    private LocalDate birthday;

    public UserRequestDto() {
    }

    public UserRequestDto(String email, String firstName, String lastName, String password, LocalDate birthday) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.birthday = birthday;
    }
}
