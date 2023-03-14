package com.kata.cinema.base.models.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
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


    public ErrorResponse(String text, HttpStatus httpStatus, int code, Date date) {
    }
}
