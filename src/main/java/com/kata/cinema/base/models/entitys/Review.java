package com.kata.cinema.base.models.entitys;

import com.kata.cinema.base.models.enums.TypeReview;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "type_reviews")
    @Enumerated(EnumType.STRING)
    private TypeReview typeReview;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    @Lob
    @JdbcTypeCode(SqlTypes.LONGVARCHAR)
    private String description;
    @Column(name = "date")
    private LocalDate date;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;
    @Column(name = "is_moderate")
    private boolean isModerate;

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", typeReview=" + typeReview +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", isModerate=" + isModerate +
                '}';
    }
}
