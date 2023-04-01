package com.crud.library.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
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
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "bookCopyId")
@Entity
@Table(name = "book_copies")
public class BookCopy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_copy_id")
    private Long bookCopyId;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private BookCopyStatus status;

    public BookCopy(Book book) {
        this.book = book;
        this.status = AVAILABLE;
    }
}
