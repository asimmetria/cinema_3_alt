package com.kata.cinema.base.models.dto.response;

import com.kata.cinema.base.models.dto.request.SearchCollectionDto;
import com.kata.cinema.base.models.dto.request.SearchMovieDto;
import com.kata.cinema.base.models.dto.request.SearchPersonDto;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchResponseDto {

    List<SearchMovieDto> movies;
    List<SearchCollectionDto> collections;
    List<SearchPersonDto> persons;
}
