package com.example.bookService;

import com.example.bookService.author.Author;
import com.example.bookService.book.Book;
import com.example.bookService.book.repository.BookRepository;
import com.example.bookService.category.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@SpringBootApplication
public class BookServiceApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(BookServiceApplication.class, args);
    }

    @Autowired
    private BookRepository bookRepository;

    @Override
    public void run(String... args){


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

        bookRepository.save(book1);
        bookRepository.save(book2);

    }
}
