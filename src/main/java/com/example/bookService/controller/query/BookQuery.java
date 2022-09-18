package com.example.bookService.controller.query;

import com.example.bookService.book.Book;
import com.example.bookService.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.Collection;
import java.util.UUID;

@Controller
public class BookQuery {

    @Autowired
    BookService bookService;

    @QueryMapping
    public Book bookById(@Argument Long id) throws Exception {
        return bookService.findById(id);
    }

    @QueryMapping
    public Collection<Book> findAllBooks(){
        return bookService.findAll();
    }

    @SchemaMapping(typeName="Book", field="isbn")
    public String getIsbn(Book book) {
        return UUID.randomUUID().toString();
    }
}
