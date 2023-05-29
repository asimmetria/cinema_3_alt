package com.kata.cinema.base.models.dto.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PersonInfoDto {
    private Long id;
    private String previewUrl;
    private String fullName;
    private String originalFullName;
    private Double height;
    private LocalDate dateBirth;
    private String placeOfBirth;

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
