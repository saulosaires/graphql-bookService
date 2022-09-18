package com.example.bookService.book.validate;

import com.example.bookService.book.Book;
import com.example.bookService.exception.BookException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

@Component
public class BookValidator {

    public void validate(Book book) throws BookException {

        if (book == null)
            throw new BookException("Book Entity can not be empty");

        Map<String, Object> errors = new HashMap<>();
        validateTitle(book, errors);
        validateRating(book, errors);
        validateAuthor(book, errors);

        if (!errors.isEmpty())
            throw new BookException("Book not valid", errors);

    }

    private void validateTitle(Book book, Map<String, Object> errors) {

        if (!StringUtils.hasText(book.getTitle()))
            errors.put("title", "Book Title can not be empty");

    }

    private void validateRating(Book book, Map<String, Object> errors) {

        if (book.getRating() < 0)
            errors.put("Rating", "Book Rating can not be less than Zero");

    }

    public void validateRating(Double rating) {

        Book book = new Book();
        book.setRating(rating);

        Map<String, Object> errors = new HashMap<>();

        validateRating(book, errors);

        if (!errors.isEmpty())
            throw new BookException("Rating not valid",errors);

    }

    private void validateAuthor(Book book, Map<String, Object> errors) {

        if (book.getAuthors() == null || book.getAuthors().isEmpty())
            errors.put("Authors", "Book Should have at least one Author");

    }

}
