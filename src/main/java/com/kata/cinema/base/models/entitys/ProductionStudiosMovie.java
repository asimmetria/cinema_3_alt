package com.kata.cinema.base.models.entitys;

import jakarta.persistence.FetchType;
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
import org.hibernate.Hibernate;

import java.util.Objects;


@Entity
@Table(name = "production_studios_movies")
@Getter
@Setter
public class ProductionStudiosMovie {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    //TODO
//    @ManyToOne
//    @JoinColumn(name = "movie_id", nullable = false)
//    public Movies movies;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "performance_id", nullable = false)
    public StudiosPerformance studiosPerformance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "studios_id", nullable = false)
    public ProductionStudios productionStudios;

    public ProductionStudiosMovie() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ProductionStudiosMovie that = (ProductionStudiosMovie) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
