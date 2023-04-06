package com.crud.library.domain.dto;

import com.crud.library.domain.BorrowRecord;
import lombok.Getter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
public class UserDto {
    private Long userId;
    private String firstName;
    private String lastName;
    private LocalDate registrationDate;
    private List<BorrowRecordDto> borrowRecordList;

    public UserDto(Long userId, String firstName, String lastName, List<BorrowRecordDto> borrowRecordDtoList) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.registrationDate = LocalDate.now();
        this.borrowRecordList = borrowRecordDtoList;
    }
}
