package com.crud.library.repository;

import com.crud.library.domain.*;
import com.crud.library.domain.dto.UserDTO;
import com.crud.library.exception.UserNotFoundException;
import com.crud.library.exception.BookCopyNotFoundException;
import com.crud.library.exception.BorrowRecordNotFoundException;
import com.crud.library.mapper.UserMapper;
import com.crud.library.service.BookCopyService;
import com.crud.library.service.BookService;
import com.crud.library.service.BorrowService;
import com.crud.library.service.UserService;
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
    private BookCopyService bookCopyService;

    @Autowired
    private BookService bookService;

    @Autowired
    private BorrowService borrowService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookCopyRepository bookCopyRepository;

    @Autowired
    private BorrowRecordRepository borrowRecordRepository;

    @Autowired
    private UserMapper userMapper;


    @Test
    void testAddUser() {
//        Given
        User user = new User("Paweł", "Staszewski");
        UserDTO userDTO = userMapper.mapToUserDTO(user);
//        When
        User savedUser = userService.saveUser(userDTO);
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
        bookCopyService.changeBookCopyStatus(bookCopyId, AVAILABLE);
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
        Long qty = bookCopyService.getNbOfAvailBookCopies(book.getTitle());
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
        BorrowRecord borrowRecord = borrowService.borrowBook(userId, bookCopyId);
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
        borrowService.returnBook(borrowRecordId);
        assertEquals(BookCopyStatus.AVAILABLE, bookCopy.getStatus());
//        CleanUp
        borrowRecordRepository.deleteById(borrowRecordId);
        bookRepository.deleteById(bookId);
        userRepository.deleteById(userId);
    }
}