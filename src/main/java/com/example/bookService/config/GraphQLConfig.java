package com.example.bookService.config;

import graphql.GraphQLError;
import graphql.execution.DataFetcherExceptionHandler;
import graphql.execution.DataFetcherExceptionHandlerParameters;
import graphql.execution.DataFetcherExceptionHandlerResult;
import org.springframework.boot.autoconfigure.graphql.GraphQlSourceBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.CompletableFuture;

@Configuration
public class GraphQLConfig {

    @Bean
    public GraphQlSourceBuilderCustomizer sourceBuilderCustomizer() {
        return (builder) ->
                builder.configureGraphQl(graphQlBuilder ->
                        graphQlBuilder.defaultDataFetcherExceptionHandler(new GraphQLExceptionHandler()));
    }


    public static class GraphQLExceptionHandler implements DataFetcherExceptionHandler {


        @Override
        public CompletableFuture<DataFetcherExceptionHandlerResult> handleException(DataFetcherExceptionHandlerParameters handlerParameters) {

            DataFetcherExceptionHandlerResult result = DataFetcherExceptionHandlerResult.newResult()
                    .error((GraphQLError) handlerParameters.getException())
                    .build();

            return CompletableFuture.completedFuture(result);
        }

    }
}