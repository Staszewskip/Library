package com.crud.library.domain.dto;

import com.crud.library.domain.BookCopy;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter

public class BookDto {
    private long bookId;
    List<BookCopy> bookCopyList;
    private String title;
    private String author;
    private int year;
}


