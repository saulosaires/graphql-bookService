package com.example.bookservice.book;

import com.example.bookservice.BaseControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.test.tester.GraphQlTester;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class BookMutationTest extends BaseControllerTest {

    @Autowired
    BookService bookService;

    @Test
    void given_Book_When_CallCreateBook_Then_ShouldCreateBook() {

        String title = UUID.randomUUID().toString();
        String isbn = UUID.randomUUID().toString();

        GraphQlTester.EntityList<Long> entityList = graphQlTester.documentName("createBook")
                .variable("title", title)
                .variable("isbn", isbn)
                .variable("rating", 4.4)
                .variable("published", "1986-12-18")
                .variable("authors", new long[]{1, 2})
                .variable("categories", new long[]{1})
                .execute()
                .path("data.createBook[*]")
                .hasValue()
                .entityList(Long.class);

        Long id = entityList.get().get(0);

        assertNotNull(bookService.findById(id));

    }

    @Test
    void given_Book_When_CallUpdateBook_Then_ShouldUpdateBook() {

        Book book = bookService.findAll().iterator().next();

        String title = UUID.randomUUID().toString();
        String isbn = UUID.randomUUID().toString();

        graphQlTester.documentName("updateBook")
                .variable("id", book.getId())
                .variable("title", title)
                .variable("isbn", isbn)
                .variable("rating", 5)
                .variable("published", "2022-11-18")
                .execute()
                .path("data.updateBook[*]")
                .hasValue();

        Book bookUpdated = bookService.findById(book.getId());

        assertEquals(title, bookUpdated.getTitle());
        assertEquals(isbn, bookUpdated.getIsbn());
        assertEquals(5, bookUpdated.getRating());
        assertEquals(LocalDate.of(2022, 11, 18), bookUpdated.getPublished());

    }

    @Test
    void given_Book_When_CallUpdateBookRating_Then_ShouldUpdateOnlytheRating() {

        Book book = bookService.findAll().iterator().next();

        graphQlTester.documentName("updateBookRating")
                .variable("id", book.getId())
                .variable("rating", 5)
                .execute()
                .path("data.updateBookRating[*]")
                .hasValue();

        Book bookUpdated = bookService.findById(book.getId());

        assertEquals(book.getTitle(), bookUpdated.getTitle());
        assertEquals(book.getIsbn(), bookUpdated.getIsbn());
        assertEquals(5, bookUpdated.getRating());
        assertEquals(book.getPublished(), bookUpdated.getPublished());

    }


}
