package com.kata.cinema.base.models.dto.response;

import com.kata.cinema.base.models.enums.MovieSortType;

import java.time.LocalDate;
import java.util.List;

public class SearchMovieResponseDto {


    private Long id;
    private String name;
    private String originalName;
    private LocalDate dateRelease;
    private String previewUrl;
    private List<String> genres;

    public SearchMovieResponseDto(Long id, String name,String originalName,
                                  String previewUrl, List<String> genres,
                                  LocalDate dateRelease) {
        this.id = id;
        this.name = name;
        this.originalName = originalName;
        this.previewUrl = previewUrl;
        this.genres = genres;
        this.dateRelease = dateRelease;
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

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public LocalDate getDateRelease() {
        return dateRelease;
    }

    public void setDateRelease(LocalDate dateRelease) {
        this.dateRelease = dateRelease;
    }
}
