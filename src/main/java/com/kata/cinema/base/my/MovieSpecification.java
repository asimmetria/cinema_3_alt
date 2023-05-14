package com.kata.cinema.base.my;

import com.kata.cinema.base.models.dto.response.SearchMovieResponseDto;
import com.kata.cinema.base.models.entitys.Movie;
import com.kata.cinema.base.models.enums.MPAA;
import com.kata.cinema.base.models.enums.MovieSortType;
import com.kata.cinema.base.models.enums.RARS;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MovieSpecification {

    private static EntityManager entityManager;


    public static Page<SearchMovieResponseDto> searchMovies(
            @PathVariable Integer pageNumber,
            @RequestParam Integer itemsOnPage,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) LocalDate startDate,
            @RequestParam(required = false) LocalDate endDate,
            @RequestParam(required = false) List<String> genres,
            @RequestParam(required = false) RARS rars,
            @RequestParam(required = false) MPAA mpaa,
            @RequestParam(required = false) MovieSortType sortType
    ) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Movie> cq = cb.createQuery(Movie.class);
        Root<Movie> root = cq.from(Movie.class);

        // Add predicates for search criteria
        List<Predicate> predicates = new ArrayList<>();

        if (name != null && !name.isEmpty()) {
            predicates.add(cb.like(root.get("name"), "%" + name + "%"));
        }

        if (startDate != null && endDate != null) {
            predicates.add(cb.between(root.get("dateRelease"), startDate, endDate));
        } else if (startDate != null) {
            predicates.add(cb.greaterThanOrEqualTo(root.get("dateRelease"), startDate));
        } else if (endDate != null) {
            predicates.add(cb.lessThanOrEqualTo(root.get("dateRelease"), endDate));
        }

        if (genres != null && !genres.isEmpty()) {
            predicates.add(root.get("genres").in(genres));
        }

        if (rars != null) {
            predicates.add(cb.equal(root.get("rars"), rars));
        }

        if (mpaa != null) {
            predicates.add(cb.equal(root.get("mpaa"), mpaa));
        }

        cq.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));

        // Add order by clause
        if (sortType != null) {
            switch (sortType) {
                case DATE_RELEASE_ASC:
                    cq.orderBy(cb.asc(root.get("dateRelease")));
                    break;
                case DATE_RELEASE_DESC:
                    cq.orderBy(cb.desc(root.get("dateRelease")));
                    break;
                case NAME_ASC:
                    cq.orderBy(cb.asc(root.get("name")));
                    break;
                case NAME_DESC:
                    cq.orderBy(cb.desc(root.get("name")));
                    break;
            }
        }

        // Execute the query
        /*List<Movie> movies = entityManager.createQuery(cq)
                .setFirstResult((pageNumber - 1) * itemsOnPage)
                .setMaxResults(itemsOnPage)
                .getResultList();

                }*/
        return searchMovies();
    }
}

