package com.kata.cinema.base.repository.specification;

import com.kata.cinema.base.models.entitys.Movie;
import com.kata.cinema.base.models.enums.MPAA;
import com.kata.cinema.base.models.enums.RARS;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MovieSpecifications {

    public static Specification<Movie> searchMovies(
            String name,
            LocalDate startDate,
            LocalDate endDate,
            List<String> genres,
            RARS rars,
            MPAA mpaa
    ) {
        return (root, query, cb) -> {
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

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
