package com.crud.library.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


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

    @Column
    private String status;

    public BookCopy(Book book) {
        this.book = book;
        this.status = "available";
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
