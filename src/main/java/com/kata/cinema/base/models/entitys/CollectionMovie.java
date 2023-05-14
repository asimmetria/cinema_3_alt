package com.kata.cinema.base.models.entitys;

import jakarta.persistence.Entity;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.FetchType;
import jakarta.persistence.GenerationType;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "collection_movie_folder")
@Getter
@Setter
public class CollectionMovie {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "collection_movie_folder_id_seq")
    @SequenceGenerator(name = "collection_movie_folder_id_seq", sequenceName = "collection_movie_folder_id_seq", allocationSize = 1)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "collection_id", nullable = false)
    private Collection collection;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;

    public CollectionMovie() {
    }

}
