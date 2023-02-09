package com.crud.library.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@Getter
@Entity(name = "BOOKS")
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

    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }
}
