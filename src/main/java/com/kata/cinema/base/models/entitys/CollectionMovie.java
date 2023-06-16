package com.kata.cinema.base.models.entitys;


import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.NamedEntityGraphs;
import jakarta.persistence.NamedSubgraph;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
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
