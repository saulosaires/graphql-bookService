package com.example.bookService.exception;

import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

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
    public String getMessage() {
        return super.getMessage();
    }

    @Override
    public List<SourceLocation> getLocations() {
        return null;
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