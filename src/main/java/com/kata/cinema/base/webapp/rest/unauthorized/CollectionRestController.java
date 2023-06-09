package com.kata.cinema.base.webapp.rest.unauthorized;

import com.kata.cinema.base.exception.CollectionNotFoundException;
import com.kata.cinema.base.exception.MovieNotFoundException;
import com.kata.cinema.base.models.dto.request.AddMovieToCollectionDTO;
import com.kata.cinema.base.models.dto.request.CollectionRequestDto;
import com.kata.cinema.base.models.dto.request.DeleteMovieFromCollectionDTO;
import com.kata.cinema.base.models.dto.response.CollectionMoviesResponseDto;
import com.kata.cinema.base.models.dto.response.CollectionResponseDto;
import com.kata.cinema.base.models.entitys.User;
import com.kata.cinema.base.models.enums.CollectionSortType;
import com.kata.cinema.base.webapp.facade.unauthorized.CollectionServiceFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/collections")
@RequiredArgsConstructor
public class CollectionRestController {

    private final CollectionServiceFacade collectionServiceFacade;

    @GetMapping
    public ResponseEntity<List<CollectionResponseDto>> getCollections(@RequestParam(required = false) Long categoryId) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (categoryId == null) {
            return ResponseEntity.ok(collectionServiceFacade.getAllCollections(user.getId()));
        }
        return ResponseEntity.ok(collectionServiceFacade.getCollectionsByCategoryId(categoryId, user.getId()));
    }

    @PostMapping
    public ResponseEntity<Void> createNewCollection(@RequestBody CollectionRequestDto requestDto) {
        collectionServiceFacade.save(requestDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCollection(@RequestBody CollectionRequestDto requestDto,
                                          @PathVariable Long id) {
        collectionServiceFacade.update(requestDto, id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCollection(@PathVariable Long id) {
        collectionServiceFacade.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivateCollection(@PathVariable Long id) {
        collectionServiceFacade.deactivateById(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/activate")
    public ResponseEntity<Void> activateCollection(@PathVariable Long id) {
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

    @GetMapping("{id}/movies/page/{pageNumber}")
    public ResponseEntity<CollectionMoviesResponseDto> getCollectionMovies(@PathVariable Long id,
                                                                           @PathVariable int pageNumber,
                                                                           @RequestParam(value = "itemsOnPage", required = false, defaultValue = "10") Long size,
                                                                           @RequestParam(value = "countryId", required = false) Long countryId,
                                                                           @RequestParam(value = "genreId", required = false) Long genreId,
                                                                           @RequestParam(value = "date", required = false) LocalDate date,
                                                                           @RequestParam(value = "collectionSortType", defaultValue = "ORDER") CollectionSortType collectionSortType) {
        return ResponseEntity.ok(collectionServiceFacade.getCollectionMovie(id, countryId, genreId, date, collectionSortType, pageNumber, size));

    }

}
