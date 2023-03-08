package com.kata.cinema.base.webapp.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ExceptionRestController {

    @ExceptionHandler()
    public ResponseEntity<ErrorResponse> globalException(RuntimeException exception) {

        return new ResponseEntity<>(new ErrorResponse(HttpStatus.NOT_FOUND.value(), exception.getMessage(), HttpStatus.NOT_FOUND).getHttpStatus());
    }
}
