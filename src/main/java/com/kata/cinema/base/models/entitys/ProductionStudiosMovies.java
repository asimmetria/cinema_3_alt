package com.kata.cinema.base.models.entitys;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;


@Entity
@Table(name = "production_studios_movies")
@Getter
@Setter
@ToString
public class ProductionStudiosMovies {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    //TODO
//    @ManyToOne
//    @JoinColumn(name = "movie_id", nullable = false)
//    public Movies movies;

    @ManyToOne
    @JoinColumn(name = "performance_id", nullable = false)
    public StudiosPerformance studiosPerformance;

    @ManyToOne
    @JoinColumn(name = "studios_id", nullable = false)
    public ProductionStudios productionStudios;

    public ProductionStudiosMovies() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductionStudiosMovies that)) return false;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
