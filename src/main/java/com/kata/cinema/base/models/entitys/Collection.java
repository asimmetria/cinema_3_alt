package com.kata.cinema.base.models.entitys;

import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
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

    @Lob
    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private CollectionCategories category;

    @Column(name = "collection_url")
    private String collectionUrl;

    public Collection() {
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
