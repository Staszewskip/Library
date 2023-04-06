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

    private Long borrowId;
    private Long userId;
    private String bookCopyTitle;
    private LocalDate borrowDate;

    public BorrowRecordDto(Long borrowId, User user, BookCopy bookCopy) {
        this.borrowId = borrowId;
        this.userId = user.getUserId();
        this.bookCopyTitle = bookCopy.getBook().getTitle();
        this.borrowDate = LocalDate.now();
    }
}
