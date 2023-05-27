package com.kata.cinema.base.models.dto.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PersonInfoDto {
    public Long id;
    public String previewUrl;
    public String fullName;
    public String originalFullName;
    public Double height;
    public LocalDate dateBirth;
    public String placeOfBirth;

    public PersonInfoDto () {
    }

    public PersonInfoDto (Long id, String previewUrl, String fullName, String originalFullName, Double height, LocalDate dateBirth, String placeOfBirth) {
        this.id = id;
        this.previewUrl = previewUrl;
        this.fullName = fullName;
        this.originalFullName = originalFullName;
        this.height = height;
        this.dateBirth = dateBirth;
        this.placeOfBirth = placeOfBirth;
    }

}
