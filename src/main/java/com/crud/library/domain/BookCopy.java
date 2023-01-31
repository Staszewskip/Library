package com.crud.library.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table
public class BookCopy {
    @Id
    @GeneratedValue
    private long bookCopyId;

    @ManyToOne
    @JoinColumn(name = "BOOK_ID")
    private Book book;


}
