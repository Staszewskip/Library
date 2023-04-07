package com.crud.library.service;

import com.crud.library.domain.*;
import com.crud.library.domain.dto.BookCopyDto;
import com.crud.library.domain.dto.BookDto;
import com.crud.library.domain.dto.BorrowRecordDto;
import com.crud.library.domain.dto.UserDto;
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

    public User saveUser(final UserDto userDto) {
        User user = libraryMapper.mapToUser(userDto);
        userRepository.save(user);
        return user;
    }

    public void saveBook(final BookDto bookDto) {
        Book book = libraryMapper.mapToBook(bookDto);
        bookRepository.save(book);
    }

    public void saveBookCopy(final Long bookId) throws BookNotFoundException {
        Book book = bookRepository.findById(bookId).orElseThrow(BookNotFoundException::new);
        BookCopy bookCopy = new BookCopy(book);
        bookCopyRepository.save(bookCopy);
    }

    public List<BookDto> showAllBooks() {
        List<Book> bookList = bookRepository.findAll();
        return libraryMapper.mapToBookDtoList(bookList);
    }

    public List<BookCopyDto> showAllBookCopies() {
        List<BookCopy> bookCopyList = bookCopyRepository.findAll();
        return libraryMapper.mapToBookCopyDtoList(bookCopyList);
    }

    public List<UserDto> showAllUsers() {
        List<User> userList = userRepository.findAll();
        return libraryMapper.mapToUserDtoList(userList);
    }

    public void changeBookCopyStatus(final Long bookCopyId, BookCopyStatus status) throws BookCopyNotFoundException {
        BookCopy bookCopy = bookCopyRepository.findById(bookCopyId).orElseThrow(BookCopyNotFoundException::new);
        bookCopy.setStatus(status);
    }

    public long getNbOfAvailBookCopies(String title) {
        return bookCopyRepository.nbOfAvailBookCopies(title);
    }

    public List<BorrowRecordDto> showAllBorrowsByUser(Long userId) throws UserNotFoundException {
        List<BorrowRecord> borrowRecordList = borrowRecordRepository.findByUserId(userId).orElseThrow(
                UserNotFoundException::new);
        return libraryMapper.mapToBorrowRecordDtoList(borrowRecordList);
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

    public void deleteBorrowRecord(Long borrowRecordId) throws BorrowRecordNotFoundException {
        BorrowRecord borrowRecord = borrowRecordRepository.findById(borrowRecordId).orElseThrow(BorrowRecordNotFoundException::new);
        borrowRecordRepository.delete(borrowRecord);
    }
}
