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
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "borrowId")
@Entity
public class BorrowRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long borrowId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "book_copy_id")
    private BookCopy bookCopy;

    @Column
    private LocalDate borrowDate;

    @Column
    private LocalDate returnDate;

    @Column
    private boolean isReturned;

    public BorrowRecord(User user, BookCopy bookCopy) {
        this.user = user;
        this.bookCopy = bookCopy;
        this.borrowDate = LocalDate.now();
        this.isReturned = false;
    }
}
