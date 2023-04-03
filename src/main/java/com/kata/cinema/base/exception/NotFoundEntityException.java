package com.kata.cinema.base.exception;

public class NotFoundEntityException extends RuntimeException {

    public NotFoundEntityException(String message) {
        super(message);
    }
}
