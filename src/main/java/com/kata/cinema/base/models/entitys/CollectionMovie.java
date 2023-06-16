package com.kata.cinema.base.models.entitys;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "collection_movies")
@Getter
@Setter
@NamedEntityGraphs({
        @NamedEntityGraph(name = "collectionMovieGraph",
                attributeNodes = {@NamedAttributeNode(value = "movie", subgraph = "ColGenreCountryGraph")
                },
                subgraphs = {
                        @NamedSubgraph(
                                name = "ColGenreCountryGraph",
                                attributeNodes = {
                                        @NamedAttributeNode(value = "genre"),
                                        @NamedAttributeNode(value = "country"),
                                        @NamedAttributeNode(value = "scores"),
                                        @NamedAttributeNode(value = "dateRelease")
                                }
                        )
                })
})
public class CollectionMovie {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "collection_movie_folder_id_seq")
    @SequenceGenerator(name = "collection_movie_folder_id_seq", sequenceName = "collection_movie_folder_id_seq", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "collection_id", nullable = false)
    private Collection collection;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;

    public CollectionMovie() {
    }

}
