package com.kata.cinema.base.webapp.rest.unauthorized;

import com.kata.cinema.base.models.dto.request.ExcertionRequestDto;
import com.kata.cinema.base.models.dto.response.ExcertionResponseDto;
import com.kata.cinema.base.models.dto.response.ReviewResponseDto;
import com.kata.cinema.base.models.enums.ReviewSortType;
import com.kata.cinema.base.models.enums.TypeReview;
import com.kata.cinema.base.webapp.facade.unauthorized.ExcertionServiceFacade;
import com.kata.cinema.base.webapp.facade.unauthorized.ReviewServiceFacade;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.kata.cinema.base.models.dto.response.MovieViewResponseDto;
import com.kata.cinema.base.webapp.facade.admin.MovieServiceFacade;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/movies")
@AllArgsConstructor
public class MovieRestController {
    private final MovieServiceFacade movieServiceFacade;
    private final ExcertionServiceFacade excertionServiceFacade;
    private final ReviewServiceFacade reviewServiceFacade;

    @GetMapping("/{id}")
    public ResponseEntity<MovieViewResponseDto> getMovie(@PathVariable Long id) {
        MovieViewResponseDto movieDto = movieServiceFacade.getMovie(id);
        if (movieDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(movieDto, HttpStatus.OK);
    }

    @GetMapping("/{id}/excertions/page/{pageNumber}")
    public Page<ExcertionResponseDto> getExcertion(@PathVariable Long id,
                                                   @PathVariable int pageNumber,
                                                   @RequestParam(value = "itemsOnPage", required = false) int size) {
        return excertionServiceFacade.getMovieExcertion(id, pageNumber, size);
    }

    @PostMapping("/{id}/excertions")
    public ResponseEntity<Void> saveExcertion(@PathVariable Long id, @RequestBody ExcertionRequestDto excertionDto) {
        excertionServiceFacade.createMovieExcertion(id, excertionDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/reviews/page/{pageNumber}")
    public Page<ReviewResponseDto> getReview(@PathVariable Long id,
                                             @PathVariable int pageNumber,
                                             @RequestParam(value = "itemsOnPage", required = false) int size,
                                             @RequestParam(value = "reviewSortType", defaultValue = "DATE_ASC") ReviewSortType sortType,
                                             @RequestParam(value = "typeReview", required = false) Optional<TypeReview> typeReview) {
        return reviewServiceFacade.getPageReviewByMovie(id, pageNumber, size, sortType, typeReview);

    }
}
