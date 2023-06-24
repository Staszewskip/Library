package com.crud.library.mapper;

import com.crud.library.domain.Book;
import com.crud.library.domain.dto.BookCopyDTO;
import com.crud.library.domain.dto.BookDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;


@SpringBootTest
class bookMapperTest {
@Autowired
private BookMapper bookMapper;
    @Test
    void mapToBook() {
        // Given
        Book book = new Book("title","author",2000);
        BookDTO bookDto = new BookDTO(0L,"title","author",2000, Collections.emptyList());
        // When
        Book mappedBook = bookMapper.mapToBook(bookDto);
        //  Then
        Assertions.assertEquals(book,mappedBook);
    }

    @Test
    void mapToBookDTO() {
        // Given
        BookDTO bookDto = new BookDTO(null,"title","author",2000, Collections.emptyList());
        Book book = new Book("title","author",2000);
        // When
        BookDTO mappedBookDTO = bookMapper.mapToBookDTO(book);
        //  Then
        Assertions.assertEquals(bookDto,mappedBookDTO);
    }

    @Test
    void mapToBookCopyDTO() {
    }

    @Test
    void mapToBorrowRecordDTO() {
    }

    @Test
    void mapToBookDTOList() {
    }

    @Test
    void mapToBookCopyDTOList() {
    }

    @Test
    void mapToBorrowRecordDTOList() {
    }

    @Test
    void mapToUserDTOList() {
    }
}