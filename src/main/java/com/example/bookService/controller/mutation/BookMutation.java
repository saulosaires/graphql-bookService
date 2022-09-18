package com.example.bookService.controller.mutation;

import com.example.bookService.facade.BookFacade;
import com.example.bookService.book.Book;
import com.example.bookService.book.mutation.BookInput;
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
    public Book updateBook(@Argument BookInput book) throws Exception {
        return bookFacade.update(book);
    }

    @MutationMapping
    public Book updateBookRating(@Argument Long id,@Argument Double rating) throws Exception {
        return bookFacade.updateRating(id,rating);
    }

}
