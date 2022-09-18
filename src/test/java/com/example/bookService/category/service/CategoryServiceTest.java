package com.example.bookService.category.service;

import com.example.bookService.BaseIntegrationTest;
import com.example.bookService.category.Category;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class CategoryServiceTest extends BaseIntegrationTest {

    @Autowired
    CategoryService categoryService;

    @Test
    public void given_ValidId_When_callFindByID_Then_ShouldReturnEntity() {

        Category category = categoryService.findById(1L);

        assertEquals(category.getId(), 1L);
        assertEquals(category.getName(), "Romance");

    }

    @Test
    public void given_InValidId_When_callFindByID_Then_ShouldThrowException() {

        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> categoryService.findById(0L));

        assertEquals("Category not found", thrown.getMessage());

    }

}
