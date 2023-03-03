package com.kata.cinema.base.models.entitys;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"question"})
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "answer_seq")
    @SequenceGenerator(name = "answer_seq", sequenceName = "answer_seq", allocationSize = 1)
    private Long id;

    private String answer;

    @Column(name = "isRight")
    private boolean isRight;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;
}