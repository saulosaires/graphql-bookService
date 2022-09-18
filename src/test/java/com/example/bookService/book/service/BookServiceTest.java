package com.example.bookService.book.service;

import com.example.bookService.BaseIntegrationTest;
import com.example.bookService.author.Author;
import com.example.bookService.author.repository.AuthorRepository;
import com.example.bookService.book.Book;
import com.example.bookService.category.Category;
import com.example.bookService.category.repository.CategoryRepository;
import com.example.bookService.exception.BookException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;


public class BookServiceTest extends BaseIntegrationTest {

    @Autowired
    BookService bookService;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    AuthorRepository authorRepository;

    @Test
    public void given_ValidId_When_callFindByID_Then_ShouldReturnEntity() {

        Book book = bookService.findById(1L);

        assertEquals(book.getId(), 1L);
        assertEquals(book.getPublished(), LocalDate.now().minusMonths(8));
        assertEquals(book.getRating(), 4);
        assertEquals(book.getTitle(), "Narnia");

    }

    @Test
    public void given_InValidId_When_callFindByID_Then_ShouldThrowException() {

        String msg = String.format("Book not found id=[%d]", -1);

        BookException thrown = Assertions.assertThrows(BookException.class, () -> bookService.findById(-1L));

        assertEquals(msg, thrown.getMessage());

    }

    @Test
    public void when_CallFindAll_Then_ShouldReturnAllBooks() {

        Collection<Book> list = bookService.findAll();

        assertFalse(list.isEmpty());
    }

    @Test
    public void given_ValidBookDTO_When_callCreate_Then_ShouldCreateBook() {

        Book book = createBookEntity();

        Book bookCreated = bookService.create(book);

        bookCreated = bookService.findById(bookCreated.getId());

        assertEquals(book.getIsbn(), bookCreated.getIsbn());
        assertEquals(book.getPublished(), bookCreated.getPublished());
        assertEquals(book.getRating(), bookCreated.getRating());
        assertEquals(book.getTitle(), bookCreated.getTitle());

    }

    @Test
    public void given_ValidBookDTO_When_callUpdate_Then_ShouldUpdateBook() {

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
    public void given_ValidIdAndRating_When_callUpdateRating_Then_ShouldUpdateRating() {

        Long id = 1L;
        Double ratingBefore = bookService.findById(id).getRating();

        bookService.updateRating(id, ratingBefore - 0.1);

        Double ratingAfter = bookService.findById(id).getRating();

        assertEquals(ratingBefore - 0.1, ratingAfter);

    }

    @Test
    public void given_ValidIdAndInvalidRating_When_callUpdateRating_Then_ShouldNotUpdateRating() {

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
