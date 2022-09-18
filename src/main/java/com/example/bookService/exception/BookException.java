package com.example.bookService.exception;

import java.util.Map;

public class BookException extends GraphQLException {

    public BookException(String message) {
        super(message);
    }

    public BookException(String message, Map<String, Object> additionParams) {
        super(message, additionParams);
    }
}