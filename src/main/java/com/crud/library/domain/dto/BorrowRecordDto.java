package com.crud.library.domain.dto;

import com.crud.library.domain.BookCopy;
import com.crud.library.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class BorrowRecordDto {

    private long borrowId;
    private User user;
    private BookCopy bookCopy;
    private LocalDate borrowDate;
    private LocalDate returnDate;

}
