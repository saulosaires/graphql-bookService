package com.example.bookservice.category;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryrRepository;

    public Category findById(Long id) {
        return categoryrRepository.findById(id).orElseThrow(() -> new CategoryException(String.format("Category not found id=[%d]", id)));
    }
}
