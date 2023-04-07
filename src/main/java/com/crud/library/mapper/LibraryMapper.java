package com.crud.library.mapper;

import com.crud.library.domain.Book;
import com.crud.library.domain.BookCopy;
import com.crud.library.domain.BorrowRecord;
import com.crud.library.domain.User;
import com.crud.library.domain.dto.BookCopyDto;
import com.crud.library.domain.dto.BookDto;
import com.crud.library.domain.dto.BorrowRecordDto;
import com.crud.library.domain.dto.UserDto;
import com.crud.library.repository.BookRepository;
import com.crud.library.exception.BookNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LibraryMapper {
    public User mapToUser(final UserDto userDto) {
        return new User(
                userDto.getFirstName(),
                userDto.getLastName()
        );
    }

    public UserDto mapToUserDto(final User user) {
        return new UserDto(
                user.getUserId(),
                user.getFirstName(),
                user.getLastName(),
                user.getRegistrationDate(),
                mapToBorrowRecordDtoList(user.getBorrowRecordList())
        );
    }

    public Book mapToBook(final BookDto bookDto) {
        return new Book(
                bookDto.getTitle(),
                bookDto.getAuthor(),
                bookDto.getYear()
        );
    }

    public BookDto mapToBookDto(final Book book) {
        return new BookDto(
                book.getBookId(),
                book.getTitle(),
                book.getAuthor(),
                book.getYear(),
                mapToBookCopyDtoList(book.getBookCopyList())
        );
    }

    public BookCopyDto mapToBookCopyDto(final BookCopy bookCopy) {
        return new BookCopyDto(
                bookCopy.getBookCopyId(),
                bookCopy.getBook().getBookId());
    }

    public BorrowRecordDto mapToBorrowRecordDto(final BorrowRecord borrowRecord) {
        return new BorrowRecordDto(
                borrowRecord.getBorrowId(),
                borrowRecord.getUser(),
                borrowRecord.getBookCopy()
        );
    }

    public List<BookDto> mapToBookDtoList(List<Book> bookList) {
        return bookList.stream()
                .map(this::mapToBookDto)
                .collect(Collectors.toList());
    }

    public List<BookCopyDto> mapToBookCopyDtoList(List<BookCopy> bookCopyList) {
        return bookCopyList.stream()
                .map(this::mapToBookCopyDto)
                .collect(Collectors.toList());
    }

    public List<BorrowRecordDto> mapToBorrowRecordDtoList(List<BorrowRecord> BorrowRecordList) {
        return BorrowRecordList.stream()
                .map(this::mapToBorrowRecordDto)
                .collect(Collectors.toList());
    }

    public List<UserDto> mapToUserDtoList(List<User> userList) {
        return userList.stream()
                .map(this::mapToUserDto)
                .collect(Collectors.toList());
    }
}
