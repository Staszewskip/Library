package com.crud.library.domain.dto;

import com.crud.library.domain.BookCopy;
import lombok.Getter;

import java.util.List;


@Getter

public class BookDto {
    private long bookId;
    List<BookCopy> bookCopyList;
    private String title;
    private String author;
    private int year;

    public BookDto(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }
}


