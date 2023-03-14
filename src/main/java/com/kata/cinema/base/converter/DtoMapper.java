package com.kata.cinema.base.converter;

import java.util.List;

public interface DtoMapper<D, E> {
    D toDto(E e);

    List<D> toDto(List<? extends E> eList);
}
