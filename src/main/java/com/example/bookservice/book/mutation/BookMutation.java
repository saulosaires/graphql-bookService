package com.example.bookservice.book.mutation;

import com.example.bookservice.book.Book;
import com.example.bookservice.book.BookInput;
import com.example.bookservice.book.BookException;
import com.example.bookservice.book.BookFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class BookMutation {

    private final BookFacade bookFacade;

    @MutationMapping
    public Book createBook(@Argument BookInput book) {
        return bookFacade.create(book);
    }

    @MutationMapping
    public Book updateBook(@Argument BookInput book) throws BookException {
        return bookFacade.update(book);
    }

    @MutationMapping
    public Book updateBookRating(@Argument Long id, @Argument Double rating) throws BookException {
        return bookFacade.updateRating(id, rating);
    }

}
