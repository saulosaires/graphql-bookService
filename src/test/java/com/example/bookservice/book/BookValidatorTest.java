package com.example.bookservice.book;

import com.example.bookservice.author.Author;
import com.example.bookservice.category.Category;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

class BookValidatorTest {

    BookValidator validator = new BookValidator();

    @Test
    void Given_EmptyBook_When_Validate_Then_ShouldThrowException() {

        BookException thrown = Assertions.assertThrows(BookException.class, () -> validator.validate(null));

        Assertions.assertEquals("Book Entity can not be empty", thrown.getMessage());
    }

    @Test
    void Given_BookWithEmptyTitle_When_Validate_Then_ShouldThrowException() {

        Book book = createBook();
        book.setTitle(null);

        BookException thrown = Assertions.assertThrows(BookException.class, () -> validator.validate(book));

        Assertions.assertEquals("Book not valid", thrown.getMessage());
        Assertions.assertEquals(1, thrown.getExtensions().size());
        Assertions.assertEquals("Book Title can not be empty", thrown.getExtensions().get("title"));

    }

    @Test
    void Given_BookWithNegativeRating_When_Validate_Then_ShouldThrowException() {

        Book book = createBook();
        book.setRating(-5.0);

        BookException thrown = Assertions.assertThrows(BookException.class, () -> validator.validate(book));

        Assertions.assertEquals("Book not valid", thrown.getMessage());
        Assertions.assertEquals(1, thrown.getExtensions().size());
        Assertions.assertEquals("Book Rating can not be less than Zero", thrown.getExtensions().get("Rating"));

    }

    @Test
    void Given_BookWithNullAuthors_When_Validate_Then_ShouldThrowException() {

        Book book = createBook();
        book.setAuthors(null);

        BookException thrown = Assertions.assertThrows(BookException.class, () -> validator.validate(book));

        Assertions.assertEquals("Book not valid", thrown.getMessage());
        Assertions.assertEquals(1, thrown.getExtensions().size());
        Assertions.assertEquals("Book Should have at least one Author", thrown.getExtensions().get("Authors"));

    }

    @Test
    void Given_BookWithEmptyAuthors_When_Validate_Then_ShouldThrowException() {

        Book book = createBook();
        book.setAuthors(Collections.emptyList());

        BookException thrown = Assertions.assertThrows(BookException.class, () -> validator.validate(book));

        Assertions.assertEquals("Book not valid", thrown.getMessage());
        Assertions.assertEquals(1, thrown.getExtensions().size());
        Assertions.assertEquals("Book Should have at least one Author", thrown.getExtensions().get("Authors"));

    }

    @Test
    void Given_BookWithAllErrors_When_Validate_Then_ShouldThrowException() {

        Book book = createBook();
        book.setAuthors(null);
        book.setRating(-5.0);
        book.setTitle(null);

        BookException thrown = Assertions.assertThrows(BookException.class, () -> validator.validate(book));

        Assertions.assertEquals("Book not valid", thrown.getMessage());
        Assertions.assertEquals(3, thrown.getExtensions().size());
        Assertions.assertEquals("Book Should have at least one Author", thrown.getExtensions().get("Authors"));
        Assertions.assertEquals("Book Rating can not be less than Zero", thrown.getExtensions().get("Rating"));
        Assertions.assertEquals("Book Title can not be empty", thrown.getExtensions().get("title"));


    }


    private Book createBook() {

        Category category = Category.builder()
                .name(UUID.randomUUID().toString())
                .build();

        Author author = Author.builder()
                .firstName(UUID.randomUUID().toString())
                .lastName(UUID.randomUUID().toString())
                .birthDate(LocalDate.now())
                .build();

        return Book.builder().
                title(UUID.randomUUID().toString())
                .isbn(UUID.randomUUID().toString())
                .rating(5.0)
                .published(LocalDate.now())
                .categories(List.of(category))
                .authors(List.of(author)).build();

    }

}
