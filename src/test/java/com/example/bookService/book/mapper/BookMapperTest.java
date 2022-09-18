package com.example.bookService.book.mapper;

import com.example.bookService.book.Book;
import com.example.bookService.book.mutation.BookInput;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookMapperTest {


    @Test
    void Given_BookInput_When_callToBook_Then_ShouldReturnBook() {

        Long id = 0L;
        String title = UUID.randomUUID().toString();
        String isbn = UUID.randomUUID().toString();
        Double rating = 4.2;
        LocalDate published = LocalDate.now();

        BookInput bookInput = new BookInput(id, title, isbn, rating, published, null, null);

        Book book = BookMapper.INSTANCE.toBook(bookInput);

        assertEquals(book.getIsbn(), bookInput.isbn());
        assertEquals(book.getPublished(), bookInput.published());
        assertEquals(book.getRating(), bookInput.rating());
        assertEquals(book.getTitle(), bookInput.title());

    }


}
