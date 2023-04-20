package com.kata.cinema.base.webapp.rest.movie;

import com.kata.cinema.base.models.dto.response.MovieViewResponseDto;
import com.kata.cinema.base.webapp.facade.movie.MovieServiceFacade;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/movies")
@AllArgsConstructor
public class MovieRestController {
    private final MovieServiceFacade movieServiceFacade;

    @GetMapping("/{id}")
    public ResponseEntity<MovieViewResponseDto> getMovie(@PathVariable Long id) {
        MovieViewResponseDto movieDto = movieServiceFacade.getMovie(id);
        if (movieDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(movieDto, HttpStatus.OK);
    }
}
