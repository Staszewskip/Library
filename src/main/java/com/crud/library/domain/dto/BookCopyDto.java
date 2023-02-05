package com.crud.library.domain.dto;

import com.crud.library.domain.Book;
import com.crud.library.domain.BorrowRecord;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class BookCopyDto {
    private long bookCopyId;
    private Book book;
    private BorrowRecord borrowId;
    private String status;
}


