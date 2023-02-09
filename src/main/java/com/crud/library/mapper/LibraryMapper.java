package com.crud.library.mapper;

import com.crud.library.domain.Book;
import com.crud.library.domain.BookCopy;
import com.crud.library.domain.BorrowRecord;
import com.crud.library.domain.User;
import com.crud.library.domain.dto.BookCopyDto;
import com.crud.library.domain.dto.BookDto;
import com.crud.library.domain.dto.BorrowRecordDto;
import com.crud.library.domain.dto.UserDto;
import org.springframework.stereotype.Service;

@Service
public class LibraryMapper {
    public User mapToUser(final UserDto userDto) {
        return new User(
                userDto.getFirstName(),
                userDto.getLastName()
        );
    }

    public UserDto mapToUserDto(final User user) {
        return new UserDto(
                user.getFirstName(),
                user.getLastName()
        );
    }

    public Book mapToBook(final BookDto bookDto) {
        return new Book(
                bookDto.getTitle(),
                bookDto.getAuthor(),
                bookDto.getYear()
        );
    }

    public Book mapToBookDto(final Book book) {
        return new Book(
                book.getTitle(),
                book.getAuthor(),
                book.getYear()
        );
    }

    public BookCopy mapToBookCopy(final BookCopyDto bookCopyDto) {
        return new BookCopy(
                bookCopyDto.getBook()
        );
    }

    public BookCopyDto mapToBookCopy(final BookCopy bookCopy) {
        return new BookCopyDto(
                bookCopy.getBook()
        );
    }

    public BorrowRecord mapToBorrowRecord(final BorrowRecordDto borrowRecordDto) {
        return new BorrowRecord(
                borrowRecordDto.getUser(),
                borrowRecordDto.getBookCopy()
        );
    }

    public BorrowRecordDto mapToBorrowRecordDto(final BorrowRecord borrowRecord) {
        return new BorrowRecordDto(
                borrowRecord.getUser(),
                borrowRecord.getBookCopy()
        );
    }
}
