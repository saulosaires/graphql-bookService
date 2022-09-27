package com.example.bookservice.author.service;

import com.example.bookservice.author.Author;
import com.example.bookservice.author.repository.AuthorRepository;
import com.example.bookservice.exception.AuthorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl implements AuthorService{

    @Autowired
    AuthorRepository authorRepository;

    @Override
    public Author findById(Long id) {
        return authorRepository.findById(id).orElseThrow(() ->new AuthorException("Author not found" ));
    }
}
