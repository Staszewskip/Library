package com.crud.library.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@NoArgsConstructor
@Getter
@Setter
@Entity
public class BorrowRecord {
    @Id
    @GeneratedValue()
    private long borrowId;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID")
    private User user;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "BOOKCOPY_ID")
    private BookCopy bookCopy;

    @Column
    private LocalDate borrowDate;

    @Column
    private LocalDate returnDate;

    public BorrowRecord(User user, BookCopy bookCopy) {
        this.user = user;
        this.bookCopy = bookCopy;
        this.borrowDate = LocalDate.now();
    }
}
