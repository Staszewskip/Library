package com.crud.library.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;


@NoArgsConstructor
@Getter
@Entity
@Table
public class BorrowStatus {
    @Id
    @GeneratedValue
    private long borrowId;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID")
    private User user;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "BOOKCOPY_ID")
    private BookCopy bookCopy;

    @Column
    private Date borrowDate;

    @Column
    private Date returnDate;

    public BorrowStatus(long borrowId, User user, BookCopy bookCopy, Date borrowDate) {
        this.borrowId = borrowId;
        this.user = user;
        this.bookCopy = bookCopy;
        this.borrowDate = new Date();
    }
}
