package com.example.blogsearch.infrastructure.exception;

public class HttpRequestFailException extends RuntimeException{
    public HttpRequestFailException(String message) {
        super(message);
    }
}
