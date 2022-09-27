package com.example.bookservice.book.repository;

import com.example.bookservice.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

     @Modifying
     @Query("update Book book set book.rating = :rating where book.id = :id")
     void updateRating(Long id, Double rating);

}
