package com.example.bookservice.facade;

import com.example.bookservice.author.Author;
import com.example.bookservice.author.service.AuthorService;
import com.example.bookservice.book.Book;
import com.example.bookservice.book.mapper.BookMapper;
import com.example.bookservice.book.mutation.BookInput;
import com.example.bookservice.book.service.BookService;
import com.example.bookservice.category.Category;
import com.example.bookservice.category.service.CategoryService;
import com.example.bookservice.exception.BookException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookFacade {

    @Autowired
    CategoryService categoryService;

    @Autowired
    BookService bookService;

    @Autowired
    AuthorService authorService;

    public Book create(BookInput bookInput) {

        List<Author> authors = fetchAuthors(bookInput.authors());
        List<Category> categories = fetchCategories(bookInput.categories());

        Book book = BookMapper.INSTANCE.toBook(bookInput);
        book.setAuthors(authors);
        book.setCategories(categories);

        return bookService.create(book);
    }

    public Book update(BookInput bookInput) throws BookException {
        return bookService.update(BookMapper.INSTANCE.toBook(bookInput));
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
