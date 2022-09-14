package com.example.bookService.category.service;

import com.example.bookService.author.Author;
import com.example.bookService.author.repository.AuthorRepository;
import com.example.bookService.category.Category;
import com.example.bookService.category.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryrRepository;

    @Override
    public Category findById(Long id) {
        return categoryrRepository.findById(id).orElseThrow(() ->new IllegalArgumentException("Category not found" ));
    }
}
