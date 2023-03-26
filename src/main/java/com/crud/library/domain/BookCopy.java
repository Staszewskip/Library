package com.crud.library.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static com.crud.library.domain.BookCopyStatus.AVAILABLE;

@NamedQuery(name = "BookCopy.nbOfAvailBookCopies",
        query = "SELECT COUNT(*) FROM BookCopy WHERE status= 'AVAILABLE' and book.title= :title"
)
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "BOOK_COPIES")
public class BookCopy {
    @Id
    @GeneratedValue
    @Column(name = "BOOK_COPY_ID")
    private Long bookCopyId;

    @NotNull
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "BOOK_ID")
    private Book book;

    @Column(name = "STATUS")
    private BookCopyStatus status;

    public BookCopy(Long bookCopyId, Book book) {
        this.bookCopyId = bookCopyId;
        this.book = book;
        this.status = BookCopyStatus.valueOf(AVAILABLE.getStatus());
    }
}
