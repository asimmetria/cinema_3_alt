package com.kata.cinema.base.models.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MediaBodyResponseDto {
    private Long id;
    private String title;
    private String previewUrl;
    private String htmlBody;
    private LocalDateTime date;
    private Long countComment;
    private String author;

    public MediaBodyResponseDto() {
    }

    public MediaBodyResponseDto(Long id, String title, String previewUrl, String htmlBody,
                                LocalDateTime date, Long countComment, String author) {
        this.id = id;
        this.title = title;
        this.previewUrl = previewUrl;
        this.htmlBody = htmlBody;
        this.date = date;
        this.countComment = countComment;
        this.author = author;
    }
}