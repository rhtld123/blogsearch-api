package com.example.blogsearch.infrastructure.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;

public abstract class BlogSearchException extends HttpStatusCodeException {

    public BlogSearchException(HttpStatus statusCode, String statusText) {
        super(statusCode, statusText);
    }
}
