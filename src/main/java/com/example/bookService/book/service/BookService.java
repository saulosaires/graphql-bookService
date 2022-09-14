package com.example.bookService.book.service;

import com.example.bookService.book.Book;

import java.util.Collection;

public interface BookService {
    Book findById(Long id) throws Exception;

    Collection<Book> findAll();

    Book create(Book book);

    Book update(Book book) throws Exception;
}
