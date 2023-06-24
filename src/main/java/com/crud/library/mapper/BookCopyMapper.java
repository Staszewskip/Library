package com.crud.library.mapper;

import com.crud.library.domain.BookCopy;
import com.crud.library.domain.dto.BookCopyDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookCopyMapper {

    public BookCopyDTO mapToBookCopyDTO(final BookCopy bookCopy) {
        return new BookCopyDTO(
                bookCopy.getBookCopyId(),
                bookCopy.getBook().getBookId());
    }
    public List<BookCopyDTO> mapToBookCopyDTOList(List<BookCopy> bookCopyList) {
        return bookCopyList.stream()
                .map(this::mapToBookCopyDTO)
                .collect(Collectors.toList());
    }
}
