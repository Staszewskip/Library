package com.crud.library.service;

import com.crud.library.domain.*;
import com.crud.library.repository.BookCopyRepository;
import com.crud.library.repository.BookRepository;
import com.crud.library.repository.BorrowRecordRepository;
import com.crud.library.repository.UserRepository;
import exception.BookCopyNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.crud.library.domain.BookCopyStatus.AVAILABLE;
import static com.crud.library.domain.BookCopyStatus.RENTED;

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

    public BookCopy saveBookCopy(final Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalStateException(" book with id " + bookId + " not found"));
        BookCopy bookCopy = new BookCopy(bookId, book);
        return bookCopyRepository.save(bookCopy);
    }

    public List<Book> showAllBooks() {
        return bookRepository.findAll();
    }

    public List<BookCopy> showAllBookCopies() {
        return bookCopyRepository.findAll();
    }

    public List<User> showAllUsers() {
        return userRepository.findAll();
    }

    public void changeBookCopyStatus(final Long bookCopyId, BookCopyStatus status) throws BookCopyNotFoundException {
        BookCopy bookCopy = bookCopyRepository.findById(bookCopyId).orElseThrow(BookCopyNotFoundException::new);
        bookCopy.setStatus(status);
    }

    public long getNbOfAvailBookCopies(String title) {
        return bookCopyRepository.nbOfAvailBookCopies(title);
    }

    public List<BorrowRecord> showAllBorrowsByUser(Long userId) {
        return borrowRecordRepository.findByUserId(userId);
    }

    public void borrowBook(Long borrowId, Long userId, Long bookCopyId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        User user = optionalUser.get();
        Optional<BookCopy> optionalBookCopy = bookCopyRepository.findById(bookCopyId);
        BookCopy bookCopy = optionalBookCopy.get();

        BorrowRecord borrowRecord = new BorrowRecord(borrowId, user, bookCopy);
        borrowRecordRepository.save(borrowRecord);
        bookCopy.setStatus(RENTED);
        bookCopyRepository.save(bookCopy);
    }

    public void returnBook(Long borrowRecordId) {
        Optional<BorrowRecord> optionalBorrowRecord = borrowRecordRepository.findById(borrowRecordId);
        BorrowRecord borrowRecord = optionalBorrowRecord.get();

        borrowRecord.setReturnDate(LocalDate.now());
        BookCopy bookCopy = borrowRecord.getBookCopy();

        bookCopy.setStatus(AVAILABLE);
        bookCopyRepository.save(bookCopy);
    }
}
