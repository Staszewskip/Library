package com.crud.library.domain.dto;

import com.crud.library.domain.BookCopy;
import com.crud.library.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;
@AllArgsConstructor
@Getter
public class BorrowStatusDto {

    private long borrowId;
    private User user;
    private BookCopy bookCopy;
    private Date borrowDate;

}
