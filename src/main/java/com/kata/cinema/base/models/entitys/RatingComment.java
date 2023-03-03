package com.kata.cinema.base.models.entitys;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"comments", "user"})
public class RatingComment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rating_comment_seq")
    @SequenceGenerator(name = "rating_comment_seq", sequenceName = "rating_comment_seq", allocationSize = 1)
    private Long id;

//    @Enumerated(EnumType.STRING)
//    private Rating rating; TODO Enum Rating

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id")
    private Comment comment;


//  @ManyToOne(fetch = FetchType.LAZY)
//  @JoinColumn(name = "user_id")
//  private User user; TODO Связь с User
}