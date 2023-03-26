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
    private List<BorrowRecord> borrowRecordList = new ArrayList<>();

    public UserDto(Long userId, String firstName, String lastName) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.registrationDate = LocalDate.now();
    }
}
