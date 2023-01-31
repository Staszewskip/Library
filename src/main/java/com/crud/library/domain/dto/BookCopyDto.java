package com.crud.library.domain.dto;

import com.crud.library.domain.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BookCopyDto {
    private long bookCopyId;
    private Book book;
}


