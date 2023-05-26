package com.kata.cinema.base.webapp.rest.unauthorized;

import com.kata.cinema.base.exception.CollectionNotFoundException;
import com.kata.cinema.base.exception.MovieNotFoundException;
import com.kata.cinema.base.models.dto.request.AddMovieToCollectionDTO;
import com.kata.cinema.base.models.dto.request.CollectionRequestDto;
import com.kata.cinema.base.models.dto.request.DeleteMovieFromCollectionDTO;
import com.kata.cinema.base.models.dto.response.CollectionMoviesResponseDto;
import com.kata.cinema.base.models.dto.response.CollectionResponseDto;
import com.kata.cinema.base.models.enums.CollectionSortType;
import com.kata.cinema.base.webapp.facade.unauthorized.CollectionServiceFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;


import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/collections")
@RequiredArgsConstructor
public class CollectionRestController {

    private final CollectionServiceFacade collectionServiceFacade;

    @GetMapping
    ResponseEntity<List<CollectionResponseDto>> getCollections(@RequestParam(required = false) Long categoryId,
                                                               @RequestParam Long userId) {
        if (categoryId == null) {
            return ResponseEntity.ok(collectionServiceFacade.getAllCollections(userId));
        }
        return ResponseEntity.ok(collectionServiceFacade.getCollectionsByCategoryId(categoryId, userId));
        //TODO
        // Реализовать получение юзера, после добавления секьюрности
    }

    @PostMapping
    ResponseEntity<Void> createNewCollection(@RequestBody CollectionRequestDto requestDto) {
        collectionServiceFacade.save(requestDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    ResponseEntity<Void> updateCollection(@RequestBody CollectionRequestDto requestDto,
                                          @PathVariable Long id) {
        collectionServiceFacade.update(requestDto, id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteCollection(@PathVariable Long id) {
        collectionServiceFacade.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/deactivate")
    ResponseEntity<Void> deactivateCollection(@PathVariable Long id) {
        collectionServiceFacade.deactivateById(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/activate")
    ResponseEntity<Void> activateCollection(@PathVariable Long id) {
        collectionServiceFacade.activateById(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/movies")
    public ResponseEntity<String> addMoviesToCollection(@PathVariable Long id,
                                                        @RequestBody AddMovieToCollectionDTO dto) {
        try {
            collectionServiceFacade.addMoviesToCollection(id, dto.getIds());
            return ResponseEntity.ok("Movies added to collection successfully");
        } catch (CollectionNotFoundException | MovieNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}/movies")
    public ResponseEntity<String> deleteMoviesFromCollection(@PathVariable Long id,
                                                             @RequestBody DeleteMovieFromCollectionDTO dto) {
        try {
            collectionServiceFacade.deleteMoviesFromCollection(id, dto.getIds());
            return ResponseEntity.ok("Movies have been successfully removed from the collection");
        } catch (CollectionNotFoundException | MovieNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("{id}/movies")
    public ResponseEntity<CollectionMoviesResponseDto> getCollectionMovies(@PathVariable Long id,
                                                                           @RequestParam(value = "countryId", required = false) Long countryId,
                                                                           @RequestParam(value = "genre", required = false) Long genreId,
                                                                           @RequestParam(value = "date", required = false) LocalDate date,
                                                                           @RequestParam(value = "collectionSortType", defaultValue = "ORDER") CollectionSortType collectionSortType) {
        return ResponseEntity.ok(collectionServiceFacade.getCollectionMovie(id, countryId, genreId, date, collectionSortType));

    }

}
