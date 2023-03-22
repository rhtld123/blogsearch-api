package com.example.blogsearch.infrastructure.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public abstract class BlogSearchException extends RuntimeException {

    @Getter
    private final HttpStatus httpStatus;

    public BlogSearchException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
