package com.crud.library.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
@AllArgsConstructor
@Getter
public class UserDto {

    private long userId;
    private String firstname;
    private String lastname;
    private LocalDate registrationDate;
}
