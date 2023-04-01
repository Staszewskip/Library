package com.crud.library.domain.dto;

import com.crud.library.domain.BookCopy;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;


@Getter
public class BookDto {
    private Long bookId;
    private List<BookCopy> bookCopyList = new ArrayList<>();
    private String title;
    private String author;
    private int year;

    public BookDto(Long bookId, String title, String author, int year) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.year = year;
    }
}


