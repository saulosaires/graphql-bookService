package com.example.bookservice.book;

import com.example.bookservice.exception.GraphQLException;

import java.util.Map;

public class BookException extends GraphQLException {

    public BookException(String message) {
        super(message);
    }

    public BookException(String message, Map<String, Object> additionParams) {
        super(message, additionParams);
    }
}