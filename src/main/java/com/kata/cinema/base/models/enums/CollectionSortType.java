package com.kata.cinema.base.models.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;


@Getter
@RequiredArgsConstructor
public enum CollectionSortType {

    ORDER(Sort.by(Sort.Direction.ASC, "id")),
    COUNT_SCORE(Sort.by(Sort.Direction.ASC, "countScore")),
    RATING(Sort.by(Sort.Direction.ASC, "avgScore")),
    RELEASE_DATE(Sort.by(Sort.Direction.ASC, "dateRelease")),
    NAME(Sort.by(Sort.Direction.ASC, "name"));

    private final Sort sortType;
}
