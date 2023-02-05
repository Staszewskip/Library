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
                userDto.getUserId(),
                userDto.getFirstname(),
                userDto.getLastname(),
                userDto.getRegistrationDate()
        );
    }

    public UserDto mapToUserDto(final User user) {
        return new UserDto(
                user.getUserId(),
                user.getFirstname(),
                user.getLastname(),
                user.getRegistrationDate()
        );
    }

    public Book mapToBook(final BookDto bookDto) {
        return new Book(
                bookDto.getBookId(),
                bookDto.getBookCopyList(),
                bookDto.getTitle(),
                bookDto.getAuthor(),
                bookDto.getYear()
        );
    }

    public Book mapToBookDto(final Book book) {
        return new Book(
                book.getBookId(),
                book.getBookCopyList(),
                book.getTitle(),
                book.getAuthor(),
                book.getYear()
        );
    }

    public BookCopy mapToBookCopy(final BookCopyDto bookCopyDto) {
        return new BookCopy(
                bookCopyDto.getBookCopyId(),
                bookCopyDto.getBook(),
                bookCopyDto.getBorrowId(),
                bookCopyDto.getStatus()
        );
    }

    public BookCopyDto mapToBookCopy(final BookCopy bookCopy) {
        return new BookCopyDto(
                bookCopy.getBookCopyId(),
                bookCopy.getBook(),
                bookCopy.getBorrowId(),
                bookCopy.getStatus()
        );
    }

    public BorrowRecord mapToBorrowRecord(final BorrowRecordDto borrowRecordDto) {
        return new BorrowRecord(
                borrowRecordDto.getUser(),
                borrowRecordDto.getBookCopy(),
                borrowRecordDto.getBorrowDate()
        );
    }
        public BorrowRecordDto mapToBorrowRecordDto(final BorrowRecord borrowRecord) {
            return new BorrowRecordDto(
                    borrowRecord.getBorrowId(),
                    borrowRecord.getUser(),
                    borrowRecord.getBookCopy(),
                    borrowRecord.getBorrowDate(),
                    borrowRecord.getBorrowDate()
            );
    }
}
