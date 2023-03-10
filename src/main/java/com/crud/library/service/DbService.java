package com.crud.library.service;

import com.crud.library.domain.Book;
import com.crud.library.domain.BookCopy;
import com.crud.library.domain.BorrowRecord;
import com.crud.library.domain.User;
import com.crud.library.repository.BookCopyRepository;
import com.crud.library.repository.BookRepository;
import com.crud.library.repository.BorrowRecordRepository;
import com.crud.library.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class DbService {
    private final BookCopyRepository bookCopyRepository;
    private final BookRepository bookRepository;
    private final BorrowRecordRepository borrowRecordRepository;
    private final UserRepository userRepository;

    public User saveUser(final User user) {
        return userRepository.save(user);
    }

    public Book saveBook(final Book book) {
        return bookRepository.save(book);
    }

    public BookCopy saveBookCopy(final BookCopy bookCopy) {
        return bookCopyRepository.save(bookCopy);
    }

    public void changeBookCopyStatus(final BookCopy bookCopy, String status) {
        bookCopy.setStatus(status);
    }

    public long getNbOfAvailBookCopies(String title) {
        return bookCopyRepository.nbOfAvailBookCopies(title);
    }

    public void borrowBook(User user, BookCopy bookCopy) {
        BorrowRecord borrowRecord = new BorrowRecord(user, bookCopy);
        borrowRecordRepository.save(borrowRecord);
        bookCopy.setStatus("borrowed");
        bookCopyRepository.save(bookCopy);
    }

    public void returnBook(BorrowRecord borrowRecord) {
        borrowRecord.setReturnDate(LocalDate.now());
        BookCopy bookCopy = borrowRecord.getBookCopy();
        bookCopy.setStatus("returned");
        bookCopyRepository.save(bookCopy);
    }

}
