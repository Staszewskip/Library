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
import java.util.Optional;

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

    public Optional<BookCopy> findBookCopy(final long bookCopyId) {
        return bookCopyRepository.findById(bookCopyId);
    }

    public void borrowBook(User user, BookCopy bookCopy) {
        BorrowRecord borrowRecord = new BorrowRecord(user, bookCopy, LocalDate.now());
        borrowRecordRepository.save(borrowRecord);
        bookCopyRepository.delete(bookCopy);
    }


    public void returnBook(BorrowRecord borrowRecord) {
        borrowRecord.setReturnDate(LocalDate.now());
        borrowRecordRepository.save(borrowRecord);
        bookCopyRepository.save(borrowRecord.getBookCopy());
    }

}
