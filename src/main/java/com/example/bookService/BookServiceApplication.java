package com.example.bookService;

import com.example.bookService.author.Author;
import com.example.bookService.author.repository.AuthorRepository;
import com.example.bookService.book.Book;
import com.example.bookService.book.repository.BookRepository;
import com.example.bookService.category.Category;
import com.example.bookService.category.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@SpringBootApplication
public class BookServiceApplication{

    public static void main(String[] args) {
        SpringApplication.run(BookServiceApplication.class, args);
    }

}
