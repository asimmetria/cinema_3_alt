package com.kata.cinema.base.my;

import com.kata.cinema.base.models.entitys.Movie;
import com.kata.cinema.base.models.entitys.User;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Table(name = "ratings")
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