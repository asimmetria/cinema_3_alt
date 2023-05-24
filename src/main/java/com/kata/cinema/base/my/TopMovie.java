package com.kata.cinema.base.my;

import com.kata.cinema.base.models.entitys.Movie;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@Entity
public class TopMovie {

    @Id
    private Long id;

    @OneToOne
    @MapsId
    private Movie movie;

    private Integer positionRating;

    public TopMovie() {}

}

