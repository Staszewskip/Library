package com.crud.library.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Getter
@Entity
@Table(name = "BOOK_COPIES")
public class BookCopy {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="BOOKCOPY_ID", unique=true)
    public Long bookCopyId;

    @ManyToOne
    @JoinColumn(name = "BOOK_ID")
    public Book book;

    @Column(name = "STATUS")
    public String status;

    public BookCopy(Book book) {
        this.book = book;
        this.status = "available";
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
