package com.kata.cinema.base.my;

import com.kata.cinema.base.models.entitys.Movie;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "scores")
@Entity
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer score;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    public Rating() {}

    public Rating(Integer score) {
        this.score = score;
    }
}