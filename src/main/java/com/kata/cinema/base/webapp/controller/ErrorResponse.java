package com.kata.cinema.base.webapp.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Getter
@Setter
public class ErrorResponse {
    private String text;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date date;

    private HttpStatus httpStatus;
    private int code;

    public ErrorResponse() {
    }

    public ErrorResponse(int code, String text, HttpStatus httpStatus) {
    }
}
