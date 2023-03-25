package com.crud.library.repository;

import com.crud.library.domain.Book;
import com.crud.library.domain.BookCopy;
import com.crud.library.domain.BorrowRecord;
import com.crud.library.domain.User;
import com.crud.library.service.DbService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

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
    void testChangeBookCopyStatus() {
//        Given
        Book book = new Book("Harry Potter", "J.K Rowling", 2000);
        BookCopy bookCopy = new BookCopy(book);
//        When
        bookRepository.save(book);
        bookCopyRepository.save(bookCopy);
        long bookCopyId = bookCopy.getBookCopyId();
        long bookId = book.getBookId();
//         Then
        dbService.changeBookCopyStatus(bookCopy, "changed status");
        Assertions.assertEquals("changed status", bookCopy.getStatus());
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
        Assertions.assertEquals(1, qty);
//        CleanUp
        bookCopyRepository.deleteById(bookCopyId);
        bookRepository.deleteById(bookId);
    }

//    @Test
//    void testBorrowBook() {
////        Given
//        User user = new User("Paweł", "Staszewski");
//        Book book = new Book("Harry Potter", "J.K Rowling", 2000);
//        BookCopy bookCopy = new BookCopy(book);
//        BorrowRecord borrowRecord = new BorrowRecord(user, bookCopy);
//        borrowRecord.setBookCopy(bookCopy);
//        borrowRecord.setUser(user);
////        When
//        userRepository.save(user);
//        bookRepository.save(book);
//        bookCopyRepository.save(bookCopy);
//
//        long userId = user.getUserId();
//        long bookCopyId = bookCopy.getBookCopyId();
//        long bookId = book.getBookId();
//
////         Then
//        dbService.borrowBook(user, bookCopy);
//        Assertions.assertEquals("borrowed", bookCopy.getStatus());
////        CleanUp
//        userRepository.deleteById(userId);
//        bookCopyRepository.deleteById(bookCopyId);
//        bookRepository.deleteById(bookId);
//    }

    @Test
    void testReturnBook() {
//        Given
        User user = new User("Paweł", "Staszewski");
        Book book = new Book("Harry Potter", "J.K Rowling", 2000);
        BookCopy bookCopy = new BookCopy(book);
        BorrowRecord borrowRecord = new BorrowRecord(user, bookCopy);
//        When
        userRepository.save(user);
        bookRepository.save(book);
        bookCopyRepository.save(bookCopy);

        long userId = user.getUserId();
        long bookCopyId = bookCopy.getBookCopyId();
        long bookId = book.getBookId();
//         Then
        dbService.returnBook(borrowRecord);
        Assertions.assertEquals("returned", bookCopy.getStatus());
//        CleanUp
        userRepository.deleteById(userId);
        bookCopyRepository.deleteById(bookCopyId);
        bookRepository.deleteById(bookId);
    }

}