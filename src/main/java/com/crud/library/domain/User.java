package com.crud.library.domain;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    @JsonProperty("userId")
    private Long userId;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private LocalDate registrationDate;

    @OneToMany(
            targetEntity = BorrowRecord.class,
            mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    @JsonBackReference("borrowReference")
    private List<BorrowRecord> borrowRecordList = new ArrayList<>();

    public User(Long userId, String firstName, String lastName) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.registrationDate = LocalDate.now();
    }
}
