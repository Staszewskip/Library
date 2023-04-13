package com.crud.library.domain.dto;

import com.crud.library.domain.BookCopy;
import com.crud.library.domain.User;
import lombok.Getter;

import java.time.LocalDate;


@Getter
public class BorrowRecordDTO {

    private Long borrowId;
    private Long userId;
    private String bookCopyTitle;
    private LocalDate borrowDate;

    public BorrowRecordDTO(Long borrowId, User user, BookCopy bookCopy) {
        this.borrowId = borrowId;
        this.userId = user.getUserId();
        this.bookCopyTitle = bookCopy.getBook().getTitle();
        this.borrowDate = LocalDate.now();
    }
}
