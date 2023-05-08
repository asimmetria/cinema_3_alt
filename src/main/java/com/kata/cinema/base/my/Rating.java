package com.kata.cinema.base.my;

import com.kata.cinema.base.models.entitys.Movie;
import com.kata.cinema.base.models.entitys.User;
import jakarta.persistence.*;

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

    // getters and setters


    public void setScore(Integer score) {
        this.score = score;
    }

    public Movie getMovie() {
        return movie;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Integer getScore() {
        return score;
    }
}