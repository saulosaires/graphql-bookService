package com.example.bookservice.category;

import com.example.bookservice.book.Book;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;


@Entity
@Table(name = "category")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "categories")
    private Collection<Book> books;

}
