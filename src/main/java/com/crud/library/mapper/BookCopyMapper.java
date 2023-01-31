package com.crud.library.mapper;

import com.crud.library.domain.BookCopy;
import com.crud.library.domain.dto.BookCopyDto;

public class BookCopyMapper {
    public BookCopy mapToBookCopy(final BookCopyDto bookCopyDto) {
        return new BookCopy(
                bookCopyDto.getBookCopyId(),
                bookCopyDto.getBook()
        );
    }

    public BookCopyDto mapToBookCopy(final BookCopy bookCopy) {
        return new BookCopyDto(
                bookCopy.getBookCopyId(),
                bookCopy.getBook()
        );
    }
}
