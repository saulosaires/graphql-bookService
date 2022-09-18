package com.example.bookService.book.service;

import com.example.bookService.book.Book;
import com.example.bookService.book.repository.BookRepository;
import com.example.bookService.book.validate.BookValidator;
import com.example.bookService.exception.BookException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;

@Service
@Slf4j
@Transactional
public class BookServiceImpl implements BookService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    BookValidator bookValidator;

    @Override
    public Book findById(Long id) throws BookException {
        return bookRepository.findById(id).orElseThrow(() -> new BookException(String.format("Book not found id=[%d]", id)));
    }

    @Override
    public Collection<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book create(Book book) throws BookException {
        bookValidator.validate(book);
        return bookRepository.save(book);
    }

    @Override
    public Book update(Book bookInput) throws BookException {

        Book book = findById(bookInput.getId());

        book.setTitle(bookInput.getTitle());
        book.setIsbn(bookInput.getIsbn());
        book.setRating(bookInput.getRating());
        book.setPublished(bookInput.getPublished());

        bookValidator.validate(book);

        return bookRepository.save(book);
    }

    @Override
    public Book updateRating(Long id, Double rating) throws BookException {

        bookRepository.findById(id).orElseThrow(() -> new BookException(String.format("Book not found id=[%d]", id)));

        bookValidator.validateRating(rating);

        bookRepository.updateRating(id, rating);

        return bookRepository.findById(id).orElse(new Book());
    }

}
