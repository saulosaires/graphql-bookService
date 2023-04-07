package com.example.bookservice.category;

import com.example.bookservice.BaseIntegrationTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;


class CategoryServiceTest extends BaseIntegrationTest {

    @Autowired
    CategoryService categoryService;

    @Test
    void given_ValidId_When_callFindByID_Then_ShouldReturnEntity() {

        Category category = categoryService.findById(1L);

        assertEquals(1L, category.getId());
        assertEquals("Romance", category.getName());

    }

    @Test
    void given_InValidId_When_callFindByID_Then_ShouldThrowException() {

        CategoryException thrown = Assertions.assertThrows(CategoryException.class, () -> categoryService.findById(0L));

        assertEquals("Category not found", thrown.getMessage());

    }

}
