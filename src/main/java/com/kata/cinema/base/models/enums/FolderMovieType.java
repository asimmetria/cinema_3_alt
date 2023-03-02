package com.kata.cinema.base.models.enums;

public enum FolderMovieType {
    WAITING_MOVIES("Буду смотреть"),
    FAVORITE_MOVIES("Любимые фильмы"),
    VIEWED_MOVIES("Просмотренные"),
    CUSTOM("Новый список");

    private final String name;

    FolderMovieType(String name) {
        this.name = name;
    }

}
