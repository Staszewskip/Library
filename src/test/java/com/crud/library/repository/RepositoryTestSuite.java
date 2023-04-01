package com.crud.library.repository;

import com.crud.library.domain.*;
import com.crud.library.service.DbService;
import exception.BookCopyNotFoundException;
import exception.BorrowRecordNotFoundException;
import exception.UserNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static com.crud.library.domain.BookCopyStatus.AVAILABLE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class RepositoryTestSuite {
    @Autowired
    private DbService dbService;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookCopyRepository bookCopyRepository;

    @Autowired
    private BorrowRecordRepository borrowRecordRepository;

    @Test
    void testAddUser() {
//given
        User user = new User("Paweł", "Staszewski");
//    when
        userRepository.save(user);
//    then
        long id = user.getUserId();
        Optional<User> readUser = userRepository.findById(id);
        assertTrue(readUser.isPresent());

//    cleanUP
        userRepository.deleteById(id);
    }

    @Test
    void testAddBook() {
//        Given
        Book book = new Book("Harry Potter", "J.K Rowling", 2000);
//        When
        bookRepository.save(book);
        long id = book.getBookId();
//        Then
        Optional<Book> readBook = bookRepository.findById(id);
        assertTrue(readBook.isPresent());
//        cleanUp
        bookRepository.deleteById(id);
    }

    @Test
    void testAddBookCopy() {
//        Given
        Book book = new Book("Harry Potter", "J.K Rowling", 2000);
        BookCopy bookCopy = new BookCopy(book);
//     When
        bookRepository.save(book);
        bookCopyRepository.save(bookCopy);
        long bookCopyId = bookCopy.getBookCopyId();
        long bookId = book.getBookId();

//        Then
        Optional<BookCopy> readBookCopy = bookCopyRepository.findById(bookCopyId);
        assertTrue(readBookCopy.isPresent());
//        CleanUp
        bookCopyRepository.deleteById(bookCopyId);
        bookRepository.deleteById(bookId);
    }

    @Test
    void testChangeBookCopyStatus() throws BookCopyNotFoundException {
//        Given
        Book book = new Book("Harry Potter", "J.K Rowling", 2000);
        BookCopy bookCopy = new BookCopy(book);
//        When
        bookRepository.save(book);
        bookCopyRepository.save(bookCopy);
        long bookCopyId = bookCopy.getBookCopyId();
        long bookId = book.getBookId();
//         Then
        dbService.changeBookCopyStatus(bookCopyId, AVAILABLE);
        assertEquals(AVAILABLE, bookCopy.getStatus());
//        CleanUp
        bookCopyRepository.deleteById(bookCopyId);
        bookRepository.deleteById(bookId);
    }

    @Test
    void testGetNbOfAvailBookCopies() {
//        Given
        Book book = new Book("Harry Potter", "J.K Rowling", 2000);
        BookCopy bookCopy = new BookCopy(book);
//        When
        bookRepository.save(book);
        bookCopyRepository.save(bookCopy);
        Long bookCopyId = bookCopy.getBookCopyId();
        Long bookId = book.getBookId();
//         Then
        Long qty = dbService.getNbOfAvailBookCopies(book.getTitle());
        assertEquals(1, qty);
//        CleanUp
        bookCopyRepository.deleteById(bookCopyId);
        bookRepository.deleteById(bookId);
    }

//    @Test
//    void testBorrowBook() throws UserNotFoundException, BookCopyNotFoundException {
////        Given
//        User user = new User("Paweł", "Staszewski");
//        Book book = new Book("Harry Potter", "J.K Rowling", 2000);
//        BookCopy bookCopy = new BookCopy(book);
////        When
//        userRepository.save(user);
//        bookRepository.save(book);
//        book.getBookCopyList().add(bookCopy);
//        Long userId = user.getUserId();
//        Long bookId = book.getBookId();
//        Long bookCopyId = bookCopy.getBookCopyId();
//
////         Then
//        dbService.borrowBook(userId, bookCopyId);
//        Assertions.assertEquals(BookCopyStatus.RENTED, bookCopy.getStatus());
////        CleanUp
////        borrowRecordRepository.deleteById(borrowRecordId);
//        bookRepository.deleteById(bookId);
//        userRepository.deleteById(userId);
//    }


    @Test
    void testReturnBook() throws BorrowRecordNotFoundException {
//        Given
        User user = new User("Paweł", "Staszewski");
        Book book = new Book("Harry Potter", "J.K Rowling", 2000);
        BookCopy bookCopy = new BookCopy(book);
        BorrowRecord borrowRecord = new BorrowRecord(user, bookCopy);
//        When
        userRepository.save(user);
        bookRepository.save(book);
        book.getBookCopyList().add(bookCopy);
        borrowRecordRepository.save(borrowRecord);

        Long userId = user.getUserId();
        Long bookId = book.getBookId();
        Long borrowRecordId = borrowRecord.getBorrowId();
//         Then
        dbService.returnBook(borrowRecordId);
        assertEquals(BookCopyStatus.AVAILABLE, bookCopy.getStatus());
//        CleanUp
        borrowRecordRepository.deleteById(borrowRecordId);
        bookRepository.deleteById(bookId);
        userRepository.deleteById(userId);
    }

//    @Test
//    void testGetBorrowedBooksByUser() {
//        List<BorrowRecord> borrowRecord = borrowRecordRepository.findByUserId(3L);
//        assertEquals(1, borrowRecord.size());
//    }
//    @Test
//    void shouldFetchAllBooks() {
//        List<Book> bookList = bookRepository.findAll();
//        assertEquals(1, bookList.size());
//    }
}