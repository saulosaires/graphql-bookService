package com.example.bookservice.exception;

import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class GraphQLException extends RuntimeException implements GraphQLError {
    private Map<String, Object> parameters;

    public GraphQLException(String message) {
        super(message);
    }

    public GraphQLException(String message, Map<String, Object> additionParams) {
        this(message);
        this.parameters = additionParams;

    }


    @Override
    public List<SourceLocation> getLocations() {
        return Collections.emptyList();
    }

    @Override
    public ErrorType getErrorType() {
        return null;
    }

    @Override
    public Map<String, Object> getExtensions() {
        return this.parameters;
    }
}