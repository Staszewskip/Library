package com.crud.library.domain.dto;

import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
public class UserDTO {
    private Long userId;
    private String firstName;
    private String lastName;
    private LocalDate registrationDate;
    private List<BorrowRecordDTO> borrowRecordDTOList;

    public UserDTO(Long userId, String firstName, String lastName,  List<BorrowRecordDTO> borrowRecordDTOList) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.registrationDate = LocalDate.now();
        this.borrowRecordDTOList = borrowRecordDTOList;
    }
}
