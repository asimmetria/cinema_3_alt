package com.kata.cinema.base.models.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MediaBodyResponseDto {
    public Long id;
    public String title;
    public String previewUrl;
    public String htmlBody;
    public LocalDateTime date;
    public Long countComment;
    public String author;

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