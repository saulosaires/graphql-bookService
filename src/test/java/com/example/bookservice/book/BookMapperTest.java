package com.example.bookservice.book;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BookMapperTest {

    BookMapper bookMapper= new BookMapperImpl();

    @Test
    void Given_BookInput_When_callToBook_Then_ShouldReturnBook() {

        Long id = 0L;
        String title = UUID.randomUUID().toString();
        String isbn = UUID.randomUUID().toString();
        Double rating = 4.2;
        LocalDate published = LocalDate.now();

        BookInput bookInput = new BookInput(id, title, isbn, rating, published, null, null);

        Book book = bookMapper.toBook(bookInput);

        assertEquals(book.getIsbn(), bookInput.isbn());
        assertEquals(book.getPublished(), bookInput.published());
        assertEquals(book.getRating(), bookInput.rating());
        assertEquals(book.getTitle(), bookInput.title());

    }


}
