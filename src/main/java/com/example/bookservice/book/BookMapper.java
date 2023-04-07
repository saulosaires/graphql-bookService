package com.example.bookservice.book;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookMapper {

    @Mapping(target = "authors", ignore = true)
    @Mapping(target = "categories", ignore = true)
    Book toBook(BookInput bookInput);

}
