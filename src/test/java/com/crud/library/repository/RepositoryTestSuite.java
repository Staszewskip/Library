package com.crud.library.repository;

import com.crud.library.domain.*;
import com.crud.library.service.DbService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static com.crud.library.domain.BookCopyStatus.*;
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
        User user = new User(0L,"Paweł", "Staszewski");
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
        Book book = new Book(1L,"Harry Potter", "J.K Rowling", 2000);
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
        Book book = new Book(1L,"Harry Potter", "J.K Rowling", 2000);
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

//    @Test
//    void testChangeBookCopyStatus() {
////        Given
//        Book book = new Book("Harry Potter", "J.K Rowling", 2000);
//        BookCopy bookCopy = new BookCopy(book);
////        When
//        bookRepository.save(book);
//        bookCopyRepository.save(bookCopy);
//        long bookCopyId = bookCopy.getBookCopyId();
//        long bookId = book.getBookId();
////         Then
//        dbService.changeBookCopyStatus(bookCopy, AVAILABLE);
//        assertEquals(AVAILABLE, bookCopy.getStatus());
////        CleanUp
//        bookCopyRepository.deleteById(bookCopyId);
//        bookRepository.deleteById(bookId);
//    }

//    @Test
//    void testGetNbOfAvailBookCopies() {
////        Given
//        Book book = new Book("Harry Potter", "J.K Rowling", 2000);
//        BookCopy bookCopy = new BookCopy(book);
////        When
//        bookRepository.save(book);
//        bookCopyRepository.save(bookCopy);
//        Long bookCopyId = bookCopy.getBookCopyId();
//        Long bookId = book.getBookId();
////         Then
//        Long qty = dbService.getNbOfAvailBookCopies(book.getTitle());
//        assertEquals(1, qty);
////        CleanUp
//        bookCopyRepository.deleteById(bookCopyId);
//        bookRepository.deleteById(bookId);
//    }

//    @Test
//    void testBorrowBook() {
////        Given
//        User user = new User(null,"Paweł", "Staszewski");
//        userRepository.save(user);
//
//        Book book = new Book("Harry Potter", "J.K Rowling", 2000);
//        bookRepository.save(book);
//        BookCopy bookCopy = new BookCopy(book);
//        book.getBookCopyList().add(bookCopy);
//        bookRepository.save(book);
//
//        BorrowRecord borrowRecord = new BorrowRecord(user, bookCopy);
//        borrowRecord.setBookCopy(bookCopy);
//        borrowRecord.setUser(user);
////        When
//        borrowRecordRepository.save(borrowRecord);
//        Long borrowRecordId = borrowRecord.getBorrowId();
//
////         Then
//        dbService.borrowBook(user, bookCopy);
//        Assertions.assertEquals("borrowed", bookCopy.getStatus());
////        CleanUp
//        borrowRecordRepository.deleteById(borrowRecordId);
//    }

    //nie rozpoznaje Stringa z enuma
//    @Test
//    void testReturnBook() {
////        Given
//        User user = new User("Paweł", "Staszewski");
//        Book book = new Book("Harry Potter", "J.K Rowling", 2000);
//        BookCopy bookCopy = new BookCopy(book);
//        BorrowRecord borrowRecord = new BorrowRecord(user, bookCopy);
////        When
//        userRepository.save(user);
//        bookRepository.save(book);
//        bookCopyRepository.save(bookCopy);
//
//        Long userId = user.getUserId();
//        Long bookCopyId = bookCopy.getBookCopyId();
//        Long bookId = book.getBookId();
//        Long borrowRecordId = borrowRecord.getBorrowId();
//
////         Then
//        dbService.returnBook(borrowRecordId);
//        assertEquals("AVAILABLE", bookCopy.getStatus());
////        CleanUp
//        userRepository.deleteById(userId);
//        bookCopyRepository.deleteById(bookCopyId);
//        bookRepository.deleteById(bookId);
//    }

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