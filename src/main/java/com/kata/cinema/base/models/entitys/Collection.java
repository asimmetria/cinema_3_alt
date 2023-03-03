package com.kata.cinema.base.models.entitys;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.Objects;

@Entity
@Table(name = "collections")
@Getter
@Setter
@ToString
public class Collection {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "enable", nullable = false)
    private byte enable;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "collection_category_id")
    private CollectionCategory collectionCategoryId;

    //TODO
//    @ManyToMany
//    @JoinTable(
//            name = "collection_movie",
//            joinColumns = @JoinColumn(name = "collection_id"),
//            inverseJoinColumns = @JoinColumn(name = "movie_id"))
//    @ToString.Exclude
//    private Set<Movie> movies;

    public Collection() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Collection that)) return false;
        return enable == that.enable && id==that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, enable);
    }
}
