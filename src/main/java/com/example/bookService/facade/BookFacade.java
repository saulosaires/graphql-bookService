package com.example.bookService.facade;

import com.example.bookService.author.Author;
import com.example.bookService.author.service.AuthorService;
import com.example.bookService.book.Book;
import com.example.bookService.book.mapper.BookMapper;
import com.example.bookService.book.mutation.BookInput;
import com.example.bookService.book.service.BookService;
import com.example.bookService.category.Category;
import com.example.bookService.category.service.CategoryService;
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

    public Book update(BookInput bookInput) throws Exception {

        return  bookService.update(BookMapper.INSTANCE.toBook(bookInput));
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

}
