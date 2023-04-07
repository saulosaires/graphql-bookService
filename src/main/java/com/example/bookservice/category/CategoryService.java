package com.example.bookservice.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryrRepository;

    public Category findById(Long id) {
        return categoryrRepository.findById(id).orElseThrow(() -> new CategoryException(String.format("Category not found id=[%d]", id)));
    }
}
