package com.crud.library.mapper;

import com.crud.library.domain.Book;
import com.crud.library.domain.BookCopy;
import com.crud.library.domain.BorrowRecord;
import com.crud.library.domain.User;
import com.crud.library.domain.dto.BookCopyDTO;
import com.crud.library.domain.dto.BookDTO;
import com.crud.library.domain.dto.BorrowRecordDTO;
import com.crud.library.domain.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LibraryMapper {
    public User mapToUser(final UserDTO userDTO) {
        return new User(
                userDTO.getFirstName(),
                userDTO.getLastName()
        );
    }

    public UserDTO mapToUserDTO(final User user) {
        return new UserDTO(
                user.getUserId(),
                user.getFirstName(),
                user.getLastName(),
                user.getRegistrationDate(),
                mapToBorrowRecordDTOList(user.getBorrowRecordList())
        );
    }

    public Book mapToBook(final BookDTO bookDTO) {
        return new Book(
                bookDTO.getTitle(),
                bookDTO.getAuthor(),
                bookDTO.getYear()
        );
    }

    public BookDTO mapToBookDTO(final Book book) {
        return new BookDTO(
                book.getBookId(),
                book.getTitle(),
                book.getAuthor(),
                book.getYear(),
                mapToBookCopyDTOList(book.getBookCopyList())
        );
    }

    public BookCopyDTO mapToBookCopyDTO(final BookCopy bookCopy) {
        return new BookCopyDTO(
                bookCopy.getBookCopyId(),
                bookCopy.getBook().getBookId());
    }

    public BorrowRecordDTO mapToBorrowRecordDTO(final BorrowRecord borrowRecord) {
        return new BorrowRecordDTO(
                borrowRecord.getBorrowId(),
                borrowRecord.getUser(),
                borrowRecord.getBookCopy()
        );
    }

    public List<BookDTO> mapToBookDTOList(List<Book> bookList) {
        return bookList.stream()
                .map(this::mapToBookDTO)
                .collect(Collectors.toList());
    }

    public List<BookCopyDTO> mapToBookCopyDTOList(List<BookCopy> bookCopyList) {
        return bookCopyList.stream()
                .map(this::mapToBookCopyDTO)
                .collect(Collectors.toList());
    }

    public List<BorrowRecordDTO> mapToBorrowRecordDTOList(List<BorrowRecord> BorrowRecordList) {
        return BorrowRecordList.stream()
                .map(this::mapToBorrowRecordDTO)
                .collect(Collectors.toList());
    }

    public List<UserDTO> mapToUserDTOList(List<User> userList) {
        return userList.stream()
                .map(this::mapToUserDTO)
                .collect(Collectors.toList());
    }
}
