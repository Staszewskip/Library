package com.crud.library.domain.dto;

import com.crud.library.domain.BookCopy;
import com.crud.library.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;


@Getter
public class BorrowRecordDto {

    private long borrowId;

    @NonNull
    private User user;

    @NonNull
    private BookCopy bookCopy;
    @NonNull
    private LocalDate borrowDate;

    private LocalDate returnDate;

    public BorrowRecordDto(Long borrowId,User user, BookCopy bookCopy) {
        this.borrowId = borrowId;
        this.user = user;
        this.bookCopy = bookCopy;
        this.borrowDate = LocalDate.now();
    }
}
