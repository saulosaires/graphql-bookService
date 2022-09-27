package com.example.bookservice.category.service;

import com.example.bookservice.category.Category;
import com.example.bookservice.category.repository.CategoryRepository;
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
