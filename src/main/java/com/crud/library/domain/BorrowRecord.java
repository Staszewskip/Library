package com.crud.library.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Entity
public class BorrowRecord {
    @Id
    @GeneratedValue()
    private long borrowId;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID")
    @NonNull
    private User user;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @NonNull
    private BookCopy bookCopy;

    @Column
    @NonNull
    private LocalDate borrowDate;

    @Column
    private LocalDate returnDate;

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

}
