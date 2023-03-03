package com.kata.cinema.base.models.entitys;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(exclude = {"user", "media", "parentComment", "childComments"})
@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comment_seq")
    @SequenceGenerator(name = "comment_seq", sequenceName = "comment_seq", allocationSize = 1)
    private Long id;

//  @ManyToOne(fetch = FetchType.LAZY)
//  @JoinColumn(name = "user_id")
//  private User user; TODO Связь с User

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "media_id")
    private Media media;

    @Column(name = "message")
    private String message;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "is_moderate")
    private boolean isModerate;

    @Column(name = "level")
    private int level;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Comment parentComment;

    @OneToMany(mappedBy = "parentComment", fetch = FetchType.LAZY)
    private List<Comment> childComments;
}