package com.kata.cinema.base.models.entitys;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Entity
@Getter
@Setter
@ToString
@Table(name = "folders_movies_positional")
public class FolderMoviePositional {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "movie", nullable = false)
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "folder", nullable = false)
    private FolderMovie folder;

    @Column(name = "positional")
    private Integer positional;
    
}
