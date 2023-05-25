package com.kata.cinema.base.models.dto.response;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class PersonInfoDto {
    public Long id;
    public String previewUrl;
    public String fullName;
    public String originalFullName;
    public Double height;
    public LocalDate birthday;
    public String placeOfBirth;

    public PersonInfoDto () {
    }

    public PersonInfoDto (Long id, String previewUrl, String fullName, String originalFullName, Double height, LocalDate birthday, String placeOfBirth) {
        this.id = id;
        this.previewUrl = previewUrl;
        this.fullName = fullName;
        this.originalFullName = originalFullName;
        this.height = height;
        this.birthday = birthday;
        this.placeOfBirth = placeOfBirth;
    }

}
