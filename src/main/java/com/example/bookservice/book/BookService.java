package com.example.bookservice.book;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class BookService {

    private final BookRepository bookRepository;
    private final BookValidator bookValidator;

    
    public Book findById(Long id) throws BookException {
        return bookRepository.findById(id).orElseThrow(() -> new BookException(String.format("Book not found id=[%d]", id)));
    }

    
    public Collection<Book> findAll() {
        return bookRepository.findAll();
    }

    
    public Book create(Book book) throws BookException {
        bookValidator.validate(book);
        return bookRepository.save(book);
    }

    
    public Book update(Book bookInput) throws BookException {

        Book book = findById(bookInput.getId());

        book.setTitle(bookInput.getTitle());
        book.setIsbn(bookInput.getIsbn());
        book.setRating(bookInput.getRating());
        book.setPublished(bookInput.getPublished());

        bookValidator.validate(book);

        return bookRepository.save(book);
    }

    
    public Book updateRating(Long id, Double rating) throws BookException {

        Book book= bookRepository.findById(id).orElseThrow(() -> new BookException(String.format("Book not found id=[%d]", id)));

        bookValidator.validateRating(rating);

        bookRepository.updateRating(id, rating);

        return bookRepository.findById(id).orElse(book);
    }

}
