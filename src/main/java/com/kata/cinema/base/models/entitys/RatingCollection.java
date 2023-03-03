package com.kata.cinema.base.models.entitys;

import com.kata.cinema.base.models.enums.TypeRating;
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
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import java.util.Objects;

@Entity
@Table(name = "rating_collection")
@Getter
@Setter
@ToString
public class RatingCollection {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "rating")
    @Enumerated(EnumType.STRING)
    TypeRating typeRating;
    @Column(name = "account_id")
    private int account_id;
    @ManyToOne
    @JoinColumn(name = "collection_id")
    public Collection collection;

    public RatingCollection() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RatingCollection that)) return false;
        return account_id == that.account_id && id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, account_id);
    }
}
