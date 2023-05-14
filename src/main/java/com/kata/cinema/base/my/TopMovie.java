package com.kata.cinema.base.my;

import com.kata.cinema.base.models.entitys.Movie;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;



@Entity
public class TopMovie {

    @Id
    private Long id;

    @OneToOne
    @MapsId
    private Movie movie;

    private Integer positionRating;

    public TopMovie() {}

    //getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Integer getPositionRating() {
        return positionRating;
    }

    public void setPositionRating(Integer positionRating) {
        this.positionRating = positionRating;
    }
}

