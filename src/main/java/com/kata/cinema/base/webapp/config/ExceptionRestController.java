package com.kata.cinema.base.webapp.config;

import com.kata.cinema.base.exception.NoSuchConnectionException;
import com.kata.cinema.base.exception.NotFoundEntityException;
import com.kata.cinema.base.models.dto.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
@Slf4j
public class ExceptionRestController {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> globalException(RuntimeException exception) {
        log.error("Ошибка при вызове REST запроса: ", exception);
        return new ResponseEntity<>(new ErrorResponse(exception.getMessage(), HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value(), new Date()).getHttpStatus());
    }

    @ExceptionHandler(NotFoundEntityException.class)
    public ResponseEntity<ErrorResponse> notFoundEntityExceptional(NotFoundEntityException exception) {
        return new ResponseEntity<>(new ErrorResponse(exception.getMessage(), HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value(), new Date()).getHttpStatus());
    }

    @ExceptionHandler(NoSuchConnectionException.class)
    public ResponseEntity<ErrorResponse> deleteException(NoSuchConnectionException exception) {
        return new ResponseEntity<>(new ErrorResponse(exception.getMessage(), HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value(), new Date()).getHttpStatus());
    }
}