package com.crud.library.service;

import com.crud.library.domain.*;
import com.crud.library.domain.dto.BookCopyDTO;
import com.crud.library.domain.dto.BookDTO;
import com.crud.library.domain.dto.BorrowRecordDTO;
import com.crud.library.domain.dto.UserDTO;
import com.crud.library.mapper.LibraryMapper;
import com.crud.library.repository.BookCopyRepository;
import com.crud.library.repository.BookRepository;
import com.crud.library.repository.BorrowRecordRepository;
import com.crud.library.repository.UserRepository;
import com.crud.library.exception.BookCopyNotFoundException;
import com.crud.library.exception.BookNotFoundException;
import com.crud.library.exception.BorrowRecordNotFoundException;
import com.crud.library.exception.UserNotFoundException;

import javax.transaction.Transactional;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static com.crud.library.domain.BookCopyStatus.AVAILABLE;
import static com.crud.library.domain.BookCopyStatus.RENTED;

@Service
@Transactional
@RequiredArgsConstructor
public class DbService {
    private final BookCopyRepository bookCopyRepository;
    private final BookRepository bookRepository;
    private final BorrowRecordRepository borrowRecordRepository;
    private final UserRepository userRepository;
    private final LibraryMapper libraryMapper;

    public User saveUser(UserDTO userDTO) {
        User user = libraryMapper.mapToUser(userDTO);
        userRepository.save(user);
        return user;
    }

    public void saveBook(BookDTO bookDTO) {
        Book book = libraryMapper.mapToBook(bookDTO);
        bookRepository.save(book);
    }

    public void saveBookCopy(Long bookId) throws BookNotFoundException {
        Book book = bookRepository.findById(bookId).orElseThrow(BookNotFoundException::new);
        BookCopy bookCopy = new BookCopy(book);
        bookCopyRepository.save(bookCopy);
    }

    public List<BookDTO> showAllBooks() {
        List<Book> bookList = bookRepository.findAll();
        return libraryMapper.mapToBookDTOList(bookList);
    }

    public List<BookCopyDTO> showAllBookCopies() {
        List<BookCopy> bookCopyList = bookCopyRepository.findAll();
        return libraryMapper.mapToBookCopyDTOList(bookCopyList);
    }

    public List<UserDTO> showAllUsers() {
        List<User> userList = userRepository.findAll();
        return libraryMapper.mapToUserDTOList(userList);
    }

    public void changeBookCopyStatus(final Long bookCopyId, BookCopyStatus status) throws BookCopyNotFoundException {
        BookCopy bookCopy = bookCopyRepository.findById(bookCopyId).orElseThrow(BookCopyNotFoundException::new);
        bookCopy.setStatus(status);
    }

    public long getNbOfAvailBookCopies(String title) {
        return bookCopyRepository.nbOfAvailBookCopies(title);
    }

    public List<BorrowRecordDTO> showAllBorrowsByUser(Long userId) throws UserNotFoundException {
        List<BorrowRecord> borrowRecordList = borrowRecordRepository.findByUserId(userId).orElseThrow(
                UserNotFoundException::new);
        return libraryMapper.mapToBorrowRecordDTOList(borrowRecordList);
    }

    public BorrowRecord borrowBook(Long userId, Long bookCopyId) throws UserNotFoundException, BookCopyNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(
                UserNotFoundException::new);
        BookCopy bookCopy = bookCopyRepository.findById(bookCopyId).orElseThrow(
                BookCopyNotFoundException::new);

        BorrowRecord borrowRecord = new BorrowRecord(user, bookCopy);
        borrowRecordRepository.save(borrowRecord);
        bookCopy.setStatus(RENTED);
        bookCopyRepository.save(bookCopy);
        return borrowRecord;
    }

    public void returnBook(Long borrowRecordId) throws BorrowRecordNotFoundException {
        BorrowRecord borrowRecord = borrowRecordRepository.findById(borrowRecordId).orElseThrow(
                BorrowRecordNotFoundException::new);

        borrowRecord.setReturnDate(LocalDate.now());
        borrowRecord.setReturned(true);
        borrowRecordRepository.save(borrowRecord);

        BookCopy bookCopy = borrowRecord.getBookCopy();
        bookCopy.setStatus(AVAILABLE);
        bookCopyRepository.save(bookCopy);
    }

    public void deleteUser(Long userId) throws UserNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        userRepository.delete(user);
    }

    public void deleteBook(Long bookId) throws BookNotFoundException {
        Book book = bookRepository.findById(bookId).orElseThrow(BookNotFoundException::new);
        bookRepository.delete(book);
    }

    public void deleteBookCopy(Long bookCopyId) throws BookCopyNotFoundException {
        BookCopy bookCopy = bookCopyRepository.findById(bookCopyId).orElseThrow(BookCopyNotFoundException::new);
        bookCopyRepository.delete(bookCopy);
    }

    public UserDTO updateUser(final UserDTO userDTO) throws UserNotFoundException {
        User user = userRepository.findById(userDTO.getUserId()).orElseThrow(UserNotFoundException::new);
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        userRepository.save(user);
        return libraryMapper.mapToUserDTO(user);
    }

    public BookDTO updateBook(final BookDTO bookDTO) throws BookNotFoundException {
        Book book = bookRepository.findById(bookDTO.getBookId()).orElseThrow(BookNotFoundException::new);
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());
        book.setYear(bookDTO.getYear());
        bookRepository.save(book);
        return libraryMapper.mapToBookDTO(book);
    }
}
