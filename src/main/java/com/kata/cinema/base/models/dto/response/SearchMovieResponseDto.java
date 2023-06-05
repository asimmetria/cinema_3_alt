package com.kata.cinema.base.models.dto.response;



import java.time.LocalDate;
import java.util.List;

public class SearchMovieResponseDto {


    private Long id;
    private String name;
    private String originalName;
    private LocalDate dateRelease;
    private String previewUrl;
    private List<String> genre;

    public SearchMovieResponseDto(Long id, String name, String originalName,
        LocalDate dateRelease, String previewUrl, List<String> genre) {
        this.id = id;
        this.name = name;
        this.originalName = originalName;
        this.dateRelease = dateRelease;
        this.previewUrl = previewUrl;
        this.genre = genre;
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
        return genre;
    }

    public void setGenres(List<String> genre) {
        this.genre = genre;
    }

    public LocalDate getDateRelease() {
        return dateRelease;
    }

    public void setDateRelease(LocalDate dateRelease) {
        this.dateRelease = dateRelease;
    }
}

