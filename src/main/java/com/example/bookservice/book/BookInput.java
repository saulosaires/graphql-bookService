package com.example.bookservice.book;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

public record BookInput(Long id,
                        String title,
                        String isbn,
                        Double rating,
                        @DateTimeFormat(pattern = "yyyy-MM-dd")
                        LocalDate published,
                        List<Long> authors,
                        List<Long> categories) {
}
