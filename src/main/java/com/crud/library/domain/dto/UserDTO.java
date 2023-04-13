package com.crud.library.domain.dto;

import com.crud.library.domain.BorrowRecord;
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

    public UserDTO(Long userId, String firstName, String lastName, LocalDate registrationDate, List<BorrowRecordDTO> borrowRecordDTOList) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.registrationDate = registrationDate;
        this.borrowRecordDTOList = borrowRecordDTOList;
    }
}
