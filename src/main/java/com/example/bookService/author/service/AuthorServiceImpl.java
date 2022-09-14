package com.example.bookService.author.service;

import com.example.bookService.author.Author;
import com.example.bookService.author.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl implements AuthorService{

    @Autowired
    AuthorRepository authorRepository;

    @Override
    public Author findById(Long id) {
        return authorRepository.findById(id).orElseThrow(() ->new IllegalArgumentException("Author not found" ));
    }
}
