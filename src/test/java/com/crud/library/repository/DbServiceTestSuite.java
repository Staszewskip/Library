package com.crud.library.repository;

import com.crud.library.domain.*;
import com.crud.library.domain.dto.UserDto;
import com.crud.library.exception.UserNotFoundException;
import com.crud.library.mapper.LibraryMapper;
import com.crud.library.service.DbService;
import com.crud.library.exception.BookCopyNotFoundException;
import com.crud.library.exception.BorrowRecordNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static com.crud.library.domain.BookCopyStatus.AVAILABLE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class DbServiceTestSuite {
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

    @Autowired
    private LibraryMapper libraryMapper;

    @Test
    void testAddUser() {
//        Given
        User user = new User("Paweł", "Staszewski");
        UserDto userDto = libraryMapper.mapToUserDto(user);
//        When
        User savedUser = dbService.saveUser(userDto);
//        Then
        long id = savedUser.getUserId();
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

    @Test
    void testBorrowBook() throws UserNotFoundException, BookCopyNotFoundException {
//        Given
        User user = new User("Paweł", "Staszewski");
        Book book = new Book("Harry Potter", "J.K Rowling", 2000);
        BookCopy bookCopy = new BookCopy(book);
//        When
        userRepository.save(user);
        bookRepository.save(book);
        bookCopyRepository.save(bookCopy);

        book.getBookCopyList().add(bookCopy);
        bookRepository.save(book);
        Long userId = user.getUserId();
        Long bookId = book.getBookId();
        Long bookCopyId = bookCopy.getBookCopyId();
        BorrowRecord borrowRecord = dbService.borrowBook(userId, bookCopyId);
        Long borrowRecordId = borrowRecord.getBorrowId();
//         Then
        Assertions.assertEquals(BookCopyStatus.RENTED, borrowRecord.getBookCopy().getStatus());
//        CleanUp
        borrowRecordRepository.deleteById(borrowRecordId);
        bookRepository.deleteById(bookId);
        userRepository.deleteById(userId);
    }


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
}