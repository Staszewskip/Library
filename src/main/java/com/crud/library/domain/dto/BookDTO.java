package com.crud.library.domain.dto;

import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
@Getter
public class BookDTO {
    private Long bookId;
    private String title;
    private String author;
    private int year;
    private List<BookCopyDTO> bookCopyList;

    public BookDTO(Long bookId, String title, String author, int year,List<BookCopyDTO> bookCopyList) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.year = year;
        this.bookCopyList = bookCopyList;
    }
}


