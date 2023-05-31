package com.kata.cinema.base.models.dto.response;

import com.kata.cinema.base.models.entitys.Genre;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class SearchMovieResponseDto {


    private Long id;
    private String name;
    private String originalName;
    private LocalDate dateRelease;
    private String previewUrl;

    private Set<Genre> genre;

    public SearchMovieResponseDto(Long id, String name, String originalName,
                                  LocalDate dateRelease, String previewUrl, Set<Genre> genres) {
        this.id = id;
        this.name = name;
        this.originalName = originalName;
        this.dateRelease = dateRelease;
        this.previewUrl = previewUrl;
        this.genre = genres;
    }



    public String getPreviewUrl() {
        return previewUrl;
    }

    public void setPreviewUrl(String previewUrl) {
        this.previewUrl = previewUrl;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Genre> getGenres() {
        return genre;
    }

    public void setGenres(Set<Genre> genres) {
        this.genre = genres;
    }

    public LocalDate getDateRelease() {
        return dateRelease;
    }

    public void setDateRelease(LocalDate dateRelease) {
        this.dateRelease = dateRelease;
    }
}
