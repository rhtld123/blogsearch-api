package com.example.blogsearch.infrastructure.exception;

import org.springframework.http.HttpStatus;

public class HttpRequestFailException extends BlogSearchException {
    public HttpRequestFailException(HttpStatus statusCode, String statusText) {
        super(statusCode, statusText);
    }
}
