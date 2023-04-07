package com.example.bookservice;

import com.example.bookservice.author.Author;
import com.example.bookservice.author.AuthorRepository;
import com.example.bookservice.book.Book;
import com.example.bookservice.book.BookRepository;
import com.example.bookservice.category.Category;
import com.example.bookservice.category.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Component
class DataLoader {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Bean
    public void init() {

        Category romance = Category.builder().name("Romance").build();
        Category aventure = Category.builder().name("Aventure").build();
        Category fantasy = Category.builder().name("Fantasy").build();

        Author author1 = Author.builder().firstName("Laura").lastName("Carvalho pessoa")
                .birthDate(LocalDate.now().minusYears(8).minusMonths(7)).build();

        Author author2 = Author.builder().firstName("Jose").lastName("Orleans da Silva")
                .birthDate(LocalDate.now().minusYears(6).minusMonths(5)).build();

        Author author3 = Author.builder().firstName("Mario").lastName("Castro da oliveira")
                .birthDate(LocalDate.now().minusYears(4).minusMonths(3)).build();

        Book book1 = Book.builder()
                .title("Narnia")
                .isbn(UUID.randomUUID().toString())
                .rating(4.0)
                .published(LocalDate.now().minusMonths(8))
                .categories(List.of(romance))
                .authors(Arrays.asList(author1, author2))
                .build();

        Book book2 = Book.builder()
                .title("Lord of the Rings")
                .isbn(UUID.randomUUID().toString())
                .rating(4.8)
                .published(LocalDate.now().minusYears(4))
                .categories(Arrays.asList(aventure, fantasy))
                .authors(List.of(author3))
                .build();

        authorRepository.saveAll(List.of(author1, author2, author3));
        categoryRepository.saveAll(List.of(romance, aventure, fantasy));

        bookRepository.save(book1);
        bookRepository.save(book2);

    }

}
