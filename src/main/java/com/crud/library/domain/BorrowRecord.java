package com.crud.library.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NamedQuery(
        name = "BorrowRecord.findByUserId",
        query = "FROM BorrowRecord  where user.userId = :userId"
)
@NoArgsConstructor
@Getter
@Setter
@Entity
public class BorrowRecord {
    @Id
    @GeneratedValue
    private Long borrowId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "userId")
    private User user;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "BOOKCOPY_ID")
    @JsonBackReference
    private BookCopy bookCopy;

    @Column
    private LocalDate borrowDate;

    @Column
    private LocalDate returnDate;

    public BorrowRecord(Long borrowId,User user, BookCopy bookCopy) {
        this.borrowId = borrowId;
        this.user = user;
        this.bookCopy = bookCopy;
        this.borrowDate = LocalDate.now();
    }
}
