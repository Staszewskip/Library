package com.crud.library.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;
@AllArgsConstructor
@Getter
public class UserDto {

    private long userId;
    private String firstname;
    private String lastname;
    private Date registrationDate;
}
