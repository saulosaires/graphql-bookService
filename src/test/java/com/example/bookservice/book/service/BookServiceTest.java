package com.example.bookservice.book.service;

import com.example.bookservice.BaseIntegrationTest;
import com.example.bookservice.author.Author;
import com.example.bookservice.author.repository.AuthorRepository;
import com.example.bookservice.book.Book;
import com.example.bookservice.category.Category;
import com.example.bookservice.category.repository.CategoryRepository;
import com.example.bookservice.exception.BookException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class BookServiceTest extends BaseIntegrationTest {

    @Autowired
    BookService bookService;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    AuthorRepository authorRepository;

    @Test
    void given_ValidId_When_callFindByID_Then_ShouldReturnEntity() {

        Book book = bookService.findById(1L);

        assertEquals(1L, book.getId());
        assertEquals(LocalDate.now().minusMonths(8), book.getPublished() );
        assertEquals(4, book.getRating());
        assertEquals("Narnia", book.getTitle());

    }

    @Test
    void given_InValidId_When_callFindByID_Then_ShouldThrowException() {

        String msg = String.format("Book not found id=[%d]", -1);

        BookException thrown = Assertions.assertThrows(BookException.class, () -> bookService.findById(-1L));

        assertEquals(msg, thrown.getMessage());

    }

    @Test
    void when_CallFindAll_Then_ShouldReturnAllBooks() {

        Collection<Book> list = bookService.findAll();

        assertFalse(list.isEmpty());
    }

    @Test
    void given_ValidBookDTO_When_callCreate_Then_ShouldCreateBook() {

        Book book = createBookEntity();

        Book bookCreated = bookService.create(book);

        bookCreated = bookService.findById(bookCreated.getId());

        assertEquals(book.getIsbn(), bookCreated.getIsbn());
        assertEquals(book.getPublished(), bookCreated.getPublished());
        assertEquals(book.getRating(), bookCreated.getRating());
        assertEquals(book.getTitle(), bookCreated.getTitle());

    }

    @Test
    void given_ValidBookDTO_When_callUpdate_Then_ShouldUpdateBook() {

        Book book = createBookEntity();
        book.setId(1L);

        Book bookCreated = bookService.update(book);

        bookCreated = bookService.findById(bookCreated.getId());

        assertEquals(book.getIsbn(), bookCreated.getIsbn());
        assertEquals(book.getPublished(), bookCreated.getPublished());
        assertEquals(book.getRating(), bookCreated.getRating());
        assertEquals(book.getTitle(), bookCreated.getTitle());

    }

    @Test
    void given_ValidIdAndRating_When_callUpdateRating_Then_ShouldUpdateRating() {

        Long id = 1L;
        Double ratingBefore = bookService.findById(id).getRating();

        bookService.updateRating(id, ratingBefore - 0.1);

        Double ratingAfter = bookService.findById(id).getRating();

        assertEquals(ratingBefore - 0.1, ratingAfter);

    }

    @Test
    void given_ValidIdAndInvalidRating_When_callUpdateRating_Then_ShouldNotUpdateRating() {

        Long id = 1L;
        Double ratingBefore = bookService.findById(id).getRating();

        BookException thrown = Assertions.assertThrows(BookException.class, () -> bookService.updateRating(id, -1.0));

        Double ratingAfter = bookService.findById(id).getRating();

        assertEquals("Rating not valid", thrown.getMessage());
        assertEquals("Book Rating can not be less than Zero", thrown.getExtensions().get("Rating"));

        assertEquals(ratingBefore, ratingAfter);

    }

    private Book createBookEntity() {

        Category category = categoryRepository.findById(1L).get();

        Author author = authorRepository.findById(1L).get();

        return Book.builder().
                title(UUID.randomUUID().toString())
                .isbn(UUID.randomUUID().toString())
                .rating(5.0)
                .published(LocalDate.now())
                .categories(List.of(category))
                .authors(List.of(author)).build();

    }

}
