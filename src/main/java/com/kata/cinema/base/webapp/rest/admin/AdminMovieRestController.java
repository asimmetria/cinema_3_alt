package com.kata.cinema.base.webapp.rest.admin;

import com.kata.cinema.base.models.dto.request.MovieRequestDto;
import com.kata.cinema.base.webapp.facade.movie.MovieServiceFacade;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/movies")
@AllArgsConstructor
public class AdminMovieRestController {
    private final MovieServiceFacade movieServiceFacade;

    @PostMapping
    public ResponseEntity<Void> saveMovie(@RequestBody MovieRequestDto movieDto) {
        movieServiceFacade.createMovie(movieDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateMovie(@PathVariable Long id, @RequestBody MovieRequestDto movieDto) {
        movieServiceFacade.updateMovie(id, movieDto);
        return ResponseEntity.ok().build();
    }
}
