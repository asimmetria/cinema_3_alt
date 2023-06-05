package com.kata.cinema.base.webapp.config;

import com.kata.cinema.base.exception.NoSuchConnectionException;
import com.kata.cinema.base.exception.NotFoundEntityException;
import com.kata.cinema.base.models.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

//@RestControllerAdvice
public class ExceptionRestController {

//    @ExceptionHandler(RuntimeException.class)
//    public ResponseEntity<ErrorResponse> globalException(RuntimeException exception) {
//        return new ResponseEntity<>(new ErrorResponse(exception.getMessage(), HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value(), new Date()).getHttpStatus());
//    }
//
//    @ExceptionHandler(NotFoundEntityException.class)
//    public ResponseEntity<ErrorResponse> notFoundEntityExceptional(NotFoundEntityException exception) {
//        return new ResponseEntity<>(new ErrorResponse(exception.getMessage(), HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value(), new Date()).getHttpStatus());
//    }
//
//    @ExceptionHandler(NoSuchConnectionException.class)
//    public ResponseEntity<ErrorResponse> deleteException(NoSuchConnectionException exception) {
//        return new ResponseEntity<>(new ErrorResponse(exception.getMessage(), HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value(), new Date()).getHttpStatus());
//    }
}