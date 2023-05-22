package com.kata.cinema.base.models.entitys;


import com.kata.cinema.base.models.enums.MPAA;
import com.kata.cinema.base.models.enums.RARS;
import com.kata.cinema.base.models.enums.TypeMedia;
import com.kata.cinema.base.my.Rating;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.NamedEntityGraphs;
import jakarta.persistence.NamedSubgraph;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "movies")
@NamedEntityGraphs({
        @NamedEntityGraph(
                name = "movieGraph",
                attributeNodes = {
                        @NamedAttributeNode(value = "country"),
                        @NamedAttributeNode(value = "genre"),
                        @NamedAttributeNode(value = "scores"),
                        @NamedAttributeNode(value = "cast", subgraph = "movieCastGraph")
                },
                subgraphs = {
                        @NamedSubgraph(
                                name = "movieCastGraph",
                                attributeNodes = {
                                        @NamedAttributeNode(value = "profession"),
                                        @NamedAttributeNode(value = "person", subgraph = "personCastGraph")
                                }
                        ),
                        @NamedSubgraph(
                                name = "personCastGraph",
                                attributeNodes = {
                                        @NamedAttributeNode(value = "casts")
                                }
                        )
                }
        )
})
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "original_name", nullable = false)
    private String originalName;

    @ManyToMany
    @JoinTable(name = "movie_countries",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "country_id"))
    private Set<Country> country;

    @Column(name = "date_release")
    private LocalDate dateRelease;

    @Column(name = "rars")
    @Enumerated(EnumType.STRING)
    private RARS rars;

    @Column(name = "mpaa")
    @Enumerated(EnumType.STRING)
    private MPAA mpaa;

    @Column(name = "time", nullable = false)
    private int time;

    @Column(name = "description")
    private String description;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private TypeMedia type;

    @ManyToMany
    @JoinTable(name = "news_movie",
            joinColumns = @JoinColumn(name = "movie"),
            inverseJoinColumns = @JoinColumn(name = "news_id"))
    private Set<Media> media;

    private String previewUrl;

    @ManyToMany
    @JoinTable(name = "movie_genre",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private Set<Genre> genre;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private Set<Score> scores;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private List<Cast> cast;

    private String originName;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private List<Rating> ratings;

    private String title;

    private Integer year;

    private String genres;
    public Movie() {
    }

    public Movie(String title, Integer year, String genre) {
        this.title = title;
        this.year = year;
        this.genres = genre;
        this.ratings = new ArrayList<>();
    }


    public void addRating(Rating rating) {
        ratings.add(rating);
        rating.setMovie(this);
    }

    public Integer getNumRatings() {
        return ratings.size();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Movie movie = (Movie) o;
        return id != null && Objects.equals(id, movie.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
