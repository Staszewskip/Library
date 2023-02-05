package com.crud.library.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity(name = "users")
@Table
public class User {
    @Id
    @GeneratedValue
    private long userId;

    @Column
    private String firstname;

    @Column
    private String lastname;

    @Column
    private LocalDate registrationDate;


}
