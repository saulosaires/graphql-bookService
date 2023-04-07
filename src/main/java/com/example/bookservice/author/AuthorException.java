package com.example.bookservice.author;

import com.example.bookservice.exception.GraphQLException;

public class AuthorException extends GraphQLException {

    public AuthorException(String message) {
        super(message);
    }

}