package com.example.bookService;

import com.example.bookService.author.Author;
import com.example.bookService.author.repository.AuthorRepository;
import com.example.bookService.book.Book;
import com.example.bookService.book.repository.BookRepository;
import com.example.bookService.category.Category;
import com.example.bookService.category.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@SpringBootTest
@ActiveProfiles("test")
public abstract class BaseIntegrationTest {


}
