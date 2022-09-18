package com.example.bookService.author.service;

import com.example.bookService.BaseIntegrationTest;
import com.example.bookService.author.Author;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class AuthorServiceTest extends BaseIntegrationTest {

    @Autowired
    AuthorService authorService;

    @Test
    public void given_ValidId_When_callFindByID_Then_ShouldReturnEntity() {

        Author author = authorService.findById(1L);

        assertEquals(author.getId(), 1L);
        assertEquals(author.getFirstName(), "Laura");
        assertEquals(author.getLastName(), "Carvalho pessoa");
        assertEquals(author.getBirthDate(), LocalDate.now().minusYears(8).minusMonths(7));

    }


    @Test
    public void given_InValidId_When_callFindByID_Then_ShouldThrowException() {

        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> authorService.findById(0L));

        assertEquals("Author not found", thrown.getMessage());

    }

}
