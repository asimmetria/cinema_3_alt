package com.kata.cinema.base.models.entitys;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode(exclude = {"question"})
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "result_seq")
    @SequenceGenerator(name = "result_seq", sequenceName = "result_seq", allocationSize = 1)
    private Long id;

    @Column(name = "count_right_answer")
    private int countRightAnswer;

    private String result;

    @ManyToOne
    private Question question;
}