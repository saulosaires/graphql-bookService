package com.example.bookService.book.service;

import com.example.bookService.book.Book;
import com.example.bookService.exception.BookException;

import java.util.Collection;

public interface BookService {
    Book findById(Long id) throws BookException;
    Collection<Book> findAll();
    Book create(Book book) throws BookException ;
    Book update(Book book) throws BookException;
    Book updateRating(Long id, Double rating) throws BookException;
}
