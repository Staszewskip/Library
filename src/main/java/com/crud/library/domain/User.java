package com.crud.library.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity(name = "users")
@Table
public class User {
    @Id
    private long userId;

    @Column
    private String firstname;

    @Column
    private String lastname;

    @Column
    private Date registrationDate;


}
