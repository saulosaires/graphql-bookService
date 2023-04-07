package com.example.bookservice.author;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;


    public Author findById(Long id) {
        return authorRepository.findById(id).orElseThrow(() -> new AuthorException(String.format("Author not found id=[%d]", id)));
    }
}
