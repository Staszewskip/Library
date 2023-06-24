package com.crud.library.mapper;

import com.crud.library.domain.Book;
import com.crud.library.domain.dto.BookDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookMapper {
    private final BookCopyMapper bookCopyMapper;
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
                bookCopyMapper.mapToBookCopyDTOList(book.getBookCopyList())
        );
    }
    public List<BookDTO> mapToBookDTOList(List<Book> bookList) {
        return bookList.stream()
                .map(this::mapToBookDTO)
                .collect(Collectors.toList());
    }

}
