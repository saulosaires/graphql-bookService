package com.example.bookservice.category;

import com.example.bookservice.exception.GraphQLException;

public class CategoryException extends GraphQLException {

    public CategoryException(String message) {
        super(message);
    }

}