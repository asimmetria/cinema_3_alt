package com.kata.cinema.base.webapp.controller.config;

import com.kata.cinema.base.models.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class ExceptionRestController {

    @ExceptionHandler()
    public ResponseEntity<ErrorResponse> globalException(RuntimeException exception) {

        return new ResponseEntity<>(new ErrorResponse(exception.getMessage(), HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value(), new Date()).getHttpStatus());
    }
}
