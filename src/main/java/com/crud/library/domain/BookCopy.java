package com.crud.library.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NamedQuery(name = "BookCopy.nbOfAvailBookCopies",
        query = "SELECT COUNT(*) FROM BookCopy WHERE status='available' and book.title=:title"
)
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "BOOK_COPIES")
public class BookCopy {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "BOOKCOPY_ID", unique = true)
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
}
