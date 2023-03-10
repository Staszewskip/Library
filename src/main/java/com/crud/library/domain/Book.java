package com.crud.library.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="BOOK_ID", unique=true)
    public Long bookId;

    @OneToMany(targetEntity = BookCopy.class,
            mappedBy = "book",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    public List<BookCopy> bookCopyList = new ArrayList<>();

    @Column(name = "TITLE")
    public String title;

    @Column(name = "AUTHOR")
    public String author;

    @Column(name = "YEAR")
    public int year;

    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }
}
