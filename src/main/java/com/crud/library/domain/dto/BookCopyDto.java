package com.crud.library.domain.dto;

import com.crud.library.domain.BookCopyStatus;
import lombok.Getter;

import static com.crud.library.domain.BookCopyStatus.AVAILABLE;


@Getter
public class BookCopyDto {
    private Long bookCopyId;
    private Long bookId;
    private BookCopyStatus status;

    public BookCopyDto(Long bookCopyId, Long bookId) {
        this.bookCopyId = bookCopyId;
        this.bookId = bookId;
        this.status = AVAILABLE;
    }
}


