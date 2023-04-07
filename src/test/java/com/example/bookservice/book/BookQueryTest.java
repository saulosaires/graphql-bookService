package com.example.bookservice.book;

import com.example.bookservice.BaseControllerTest;
import com.example.bookservice.book.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.test.tester.GraphQlTester;

import static org.junit.jupiter.api.Assertions.assertFalse;

class BookQueryTest extends BaseControllerTest {

    @Autowired
    BookService bookService;

    @Test
    void given_BookQuery_When_CallFindAllBooks_Then_ShouldReturnAllBooks() {

        GraphQlTester.EntityList<Book> books = graphQlTester.documentName("findAllBooks")
                .execute()
                .path("data.findAllBooks[*]")
                .entityList(Book.class)
                .hasSizeGreaterThan(1);

        assertFalse(books.get().isEmpty());

    }

    @Test
    void given_BookQuery_When_CallBookById_Then_ShouldReturnExactlyTheBook() {

        Book books = bookService.findAll().iterator().next();

        graphQlTester.documentName("bookById")
                .variable("id", books.getId())
                .execute()
                .path("data.bookById[*]")
                .hasValue();

    }

}
