package com.crud.library.repository;

import com.crud.library.domain.*;
import com.crud.library.domain.dto.BookDTO;
import com.crud.library.domain.dto.UserDTO;
import com.crud.library.exception.BookNotFoundException;
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

import java.util.Collections;
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
        BookDTO bookDTO = new BookDTO(null, "Harry Potter", "J.K Rowling", 2000, Collections.emptyList());
//        When
        bookService.saveBook(bookDTO);
//        Then
        assertEquals(1, bookRepository.count());
//        cleanUp
        bookRepository.deleteAll();
    }

    @Test
    void testAddBookCopy() throws BookNotFoundException {
//        Given
        BookDTO bookDTO = new BookDTO(null, "Harry Potter", "J.K Rowling", 2000, Collections.emptyList());
//     When
        Book savedBook = bookService.saveBook(bookDTO);
        bookCopyService.saveBookCopy(savedBook.getBookId());

//        Then
        assertEquals(1, bookCopyRepository.count());
//        CleanUp
        bookRepository.deleteAll();
        bookCopyRepository.deleteAll();
    }

    @Test
    void testChangeBookCopyStatus() throws BookCopyNotFoundException, BookNotFoundException {
//        Given
        BookDTO bookDTO = new BookDTO(null, "Harry Potter", "J.K Rowling", 2000, Collections.emptyList());
//     When
        Book savedBook = bookService.saveBook(bookDTO);
        BookCopy savedBookCopy = bookCopyService.saveBookCopy(savedBook.getBookId());
//         Then
        bookCopyService.changeBookCopyStatus(savedBookCopy.getBookCopyId(), AVAILABLE);
        assertEquals(AVAILABLE, savedBookCopy.getStatus());
//        CleanUp
        bookRepository.deleteAll();
        bookCopyRepository.deleteAll();
    }

    @Test
    void testGetNbOfAvailBookCopies() throws BookNotFoundException {
//        Given
        BookDTO bookDTO = new BookDTO(null, "Harry Potter", "J.K Rowling", 2000, Collections.emptyList());
//     When
        Book savedBook = bookService.saveBook(bookDTO);
        bookCopyService.saveBookCopy(savedBook.getBookId());
//         Then
        Long qty = bookCopyService.getNbOfAvailBookCopies(savedBook.getTitle());
        assertEquals(1, qty);
//        CleanUp
        bookRepository.deleteAll();
        bookCopyRepository.deleteAll();
    }

    @Test
    void testBorrowBook() throws UserNotFoundException, BookCopyNotFoundException, BookNotFoundException {
//        Given
        UserDTO userDTO = new UserDTO(null, "Paweł", "Staszewski", Collections.emptyList());
        BookDTO bookDTO = new BookDTO(null, "Harry Potter", "J.K Rowling", 2000, Collections.emptyList());
//        When
        User savedUser = userService.saveUser(userDTO);
        Book savedBook = bookService.saveBook(bookDTO);
        BookCopy savedBookCopy = bookCopyService.saveBookCopy(savedBook.getBookId());

        savedBook.getBookCopyList().add(savedBookCopy);
        bookService.saveBook(bookDTO);
        BorrowRecord borrowRecord = borrowService.borrowBook(savedUser.getUserId(), savedBookCopy.getBookCopyId());

//         Then
        Assertions.assertEquals(BookCopyStatus.RENTED, borrowRecord.getBookCopy().getStatus());
//        CleanUp
        borrowRecordRepository.deleteAll();
        bookRepository.deleteAll();
        bookCopyRepository.deleteAll();
    }


    @Test
    void testReturnBook() throws BorrowRecordNotFoundException, BookNotFoundException, UserNotFoundException, BookCopyNotFoundException {
//        Given
        UserDTO userDTO = new UserDTO(null, "Paweł", "Staszewski", Collections.emptyList());
        BookDTO bookDTO = new BookDTO(null, "Harry Potter", "J.K Rowling", 2000, Collections.emptyList());
//        When
        User savedUser = userService.saveUser(userDTO);
        Book savedBook = bookService.saveBook(bookDTO);
        BookCopy savedBookCopy = bookCopyService.saveBookCopy(savedBook.getBookId());

        BorrowRecord savedBorrowRecord = borrowService.borrowBook(savedUser.getUserId(),savedBookCopy.getBookCopyId());

//         Then
        borrowService.returnBook(savedBorrowRecord.getBorrowId());
        assertEquals(BookCopyStatus.AVAILABLE, savedBookCopy.getStatus());
//        CleanUp
        borrowRecordRepository.deleteAll();
        bookRepository.deleteAll();
        bookCopyRepository.deleteAll();
    }
}