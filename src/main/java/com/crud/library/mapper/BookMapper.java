package com.crud.library.mapper;

import com.crud.library.domain.Book;
import com.crud.library.domain.dto.BookDto;

public class BookMapper {

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
}
