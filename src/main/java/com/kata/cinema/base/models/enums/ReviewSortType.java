package com.kata.cinema.base.models.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;

@Getter
@RequiredArgsConstructor
public enum ReviewSortType {
    DATE_ASC(Sort.by(Sort.Direction.ASC, "date")),
    DATE_DESC(Sort.by(Sort.Direction.DESC, "date"));
    private final Sort sortType;

}
