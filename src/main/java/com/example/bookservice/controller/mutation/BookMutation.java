package com.example.bookservice.controller.mutation;

import com.example.bookservice.exception.BookException;
import com.example.bookservice.facade.BookFacade;
import com.example.bookservice.book.Book;
import com.example.bookservice.book.mutation.BookInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
public class BookMutation {

    @Autowired
    BookFacade bookFacade;

    @MutationMapping
    public Book createBook(@Argument BookInput book) {
        return bookFacade.create(book);
    }

    @MutationMapping
    public Book updateBook(@Argument BookInput book) throws BookException {
        return bookFacade.update(book);
    }

    @MutationMapping
    public Book updateBookRating(@Argument Long id,@Argument Double rating) throws BookException {
        return bookFacade.updateRating(id,rating);
    }

}
