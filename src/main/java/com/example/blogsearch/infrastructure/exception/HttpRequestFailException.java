package com.example.blogsearch.infrastructure.exception;

import org.springframework.http.HttpStatus;

public class HttpRequestFailException extends BlogSearchException {
    public HttpRequestFailException(HttpStatus httpStatus, String message) {
        super(message, httpStatus);
    }
}
