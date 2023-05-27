package com.kata.cinema.base.models.dto.response;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class PersonViewResponseDto {
    public Long id;
    public String previewUrl;
    public String fullName;
    public String originalFullName;
    public Double height;
    public LocalDate dateBirth;
    public String placeOfBirth;
    public Long countMove;
    public List<GenreResponseDto> genres;
    public List<ProfessionResponseDto> professions;

    public PersonViewResponseDto () {
    }

    public PersonViewResponseDto (Long id, String previewUrl, String fullName, String originalFullName, Double height, LocalDate dateBirth, String placeOfBirth, Long countMove,List<GenreResponseDto> genres,List<ProfessionResponseDto> professions) {
        this.id = id;
        this.previewUrl = previewUrl;
        this.fullName = fullName;
        this.originalFullName = originalFullName;
        this.height = height;
        this.dateBirth = dateBirth;
        this.placeOfBirth = placeOfBirth;
        this.countMove = countMove;
        this.genres = genres;
        this.professions = professions;
    }
}
