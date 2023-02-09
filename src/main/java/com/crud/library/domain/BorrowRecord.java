package com.crud.library.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@NoArgsConstructor
@Getter
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

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public void setBookCopy(BookCopy bookCopy) {
        this.bookCopy = bookCopy;
    }
}
