package com.example.bookservice.book;

import com.example.bookservice.author.Author;
import com.example.bookservice.author.AuthorService;
import com.example.bookservice.book.Book;
import com.example.bookservice.book.BookMapper;
import com.example.bookservice.book.BookService;
import com.example.bookservice.category.Category;
import com.example.bookservice.category.CategoryService;
import com.example.bookservice.book.BookInput;
import com.example.bookservice.book.BookException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookFacade {

    private final CategoryService categoryService;
    private final BookService bookService;
    private final AuthorService authorService;
    private final BookMapper bookMapper;

    public Book create(BookInput bookInput) {

        List<Author> authors = fetchAuthors(bookInput.authors());
        List<Category> categories = fetchCategories(bookInput.categories());

        Book book = bookMapper.toBook(bookInput);
        book.setAuthors(authors);
        book.setCategories(categories);

        return bookService.create(book);
    }

    public Book update(BookInput bookInput) throws BookException {
        return bookService.update(bookMapper.toBook(bookInput));
    }

    private List<Author> fetchAuthors(List<Long> authorsId) {

        List<Author> authors = new ArrayList<>();

        if (authorsId == null) return authors;

        for (Long id : authorsId) {
            authors.add(authorService.findById(id));
        }

        return authors;
    }

    private List<Category> fetchCategories(List<Long> categoriesId) {

        List<Category> categories = new ArrayList<>();

        if (categoriesId == null) return categories;

        for (Long id : categoriesId) {
            categories.add(categoryService.findById(id));
        }

        return categories;
    }

    public Book updateRating(Long id, Double rating) {
        return bookService.updateRating(id, rating);
    }
}
