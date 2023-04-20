package com.kata.cinema.base.models.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class MoviePersonResponseDto {

    @JsonIgnore
    private Long professionId;
    private Long personId;
    private String fullName;
    private String originalFullName;

    public MoviePersonResponseDto() {
    }

    public MoviePersonResponseDto(Long professionId, Long personId, String fullName, String originalFullName) {
        this.professionId = professionId;
        this.personId = personId;
        this.fullName = fullName;
        this.originalFullName = originalFullName;
    }
}
