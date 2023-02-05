package com.crud.library.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "BOOK_COPIES")
public class BookCopy {
    @Id
    @GeneratedValue
    private long bookCopyId;

    @ManyToOne
    @JoinColumn(name = "BOOK_ID")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "BORROW_ID")
    private BorrowRecord borrowId;

    @Column
    private String status;

    public void setStatus(String status) {
        this.status = status;
    }

}
