package com.example.bookservice.author.service;

import com.example.bookservice.BaseIntegrationTest;
import com.example.bookservice.author.Author;
import com.example.bookservice.exception.AuthorException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AuthorServiceTest extends BaseIntegrationTest {

    @Autowired
    AuthorService authorService;

    @Test
    void given_ValidId_When_callFindByID_Then_ShouldReturnEntity() {

        Author author = authorService.findById(1L);

        assertEquals(1L, author.getId());
        assertEquals("Laura", author.getFirstName());
        assertEquals("Carvalho pessoa", author.getLastName());
        assertEquals(author.getBirthDate(), LocalDate.now().minusYears(8).minusMonths(7));

    }

    @Test
    void given_InValidId_When_callFindByID_Then_ShouldThrowException() {

        AuthorException thrown = Assertions.assertThrows(AuthorException.class, () -> authorService.findById(0L));

        assertEquals("Author not found", thrown.getMessage());

    }

}
