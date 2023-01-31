package com.crud.library.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity(name = "books")
public class Book {
    @Id
    @GeneratedValue
    private long bookId;

    @OneToMany(targetEntity = BookCopy.class,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    List<BookCopy> bookCopyList = new ArrayList<>();

    @Column
    private String title;

    @Column
    private String author;

    @Column
    private int year;
}
