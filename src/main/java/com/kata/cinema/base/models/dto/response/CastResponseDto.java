package com.kata.cinema.base.models.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

@Data
public class CastResponseDto {

    @JsonIgnore
    private Long movieId;
    private Long professionId;
    private String professionName;
    private List<MoviePersonResponseDto> persons;

    public CastResponseDto() {
    }

    public CastResponseDto(Long movieId, Long professionId,
                           String professionName, List<MoviePersonResponseDto> persons) {
        this.movieId = movieId;
        this.professionId = professionId;
        this.professionName = professionName;
        this.persons = persons;
    }
}
