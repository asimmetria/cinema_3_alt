package com.kata.cinema.base.models.entitys;

import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;
import org.hibernate.Hibernate;

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
    private Boolean enable = true;

    @Lob
    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private CollectionCategories category;

    @Column(name = "collection_url")
    private String collectionUrl;

    @OneToMany(mappedBy = "collection")
    private Set<CollectionMovie> collectionMovies;


    public Collection() {
    }

    public int getCountMovies() {
        return collectionMovies.size();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Collection that = (Collection) o;
        return enable == that.enable && Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(category, that.category) && Objects.equals(collectionUrl, that.collectionUrl);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
