package com.example.bookService.book.service;

import com.example.bookService.book.Book;
import com.example.bookService.book.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collection;

@Service
@Slf4j
public class BookServiceImpl implements BookService {

    @Autowired
    BookRepository bookRepository;

    @Override
    public Book findById(Long id) throws Exception {
        return bookRepository.findById(id).orElseThrow(() ->new IllegalArgumentException("Book not found " ));
    }

    @Override
    public Collection<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book create(Book book) {

        return bookRepository.save(book);
    }

    @Override
    public Book update(Book bookInput) throws Exception {

        Book book= findById(bookInput.getId());

        book.setTitle(bookInput.getTitle());
        book.setIsbn(bookInput.getIsbn());
        book.setRating(bookInput.getRating());
        book.setPublished(bookInput.getPublished());

        return bookRepository.save(book);
    }
}
