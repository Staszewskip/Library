package com.crud.library.service;

import com.crud.library.domain.Book;
import com.crud.library.domain.BookCopy;
import com.crud.library.domain.BookCopyStatus;
import com.crud.library.domain.dto.BookCopyDTO;
import com.crud.library.exception.BookCopyNotFoundException;
import com.crud.library.exception.BookNotFoundException;
import com.crud.library.mapper.BookCopyMapper;
import com.crud.library.repository.BookCopyRepository;
import com.crud.library.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BookCopyService {
    private final BookCopyRepository bookCopyRepository;
    private final BookRepository bookRepository;
    private final BookCopyMapper bookCopyMapper;
    public BookCopy saveBookCopy(Long bookId) throws BookNotFoundException {
        Book book = bookRepository.findById(bookId).orElseThrow(BookNotFoundException::new);
        BookCopy bookCopy = new BookCopy(book);
        bookCopyRepository.save(bookCopy);
        return bookCopy;
    }
    public void deleteBookCopy(Long bookCopyId) throws BookCopyNotFoundException {
        BookCopy bookCopy = bookCopyRepository.findById(bookCopyId).orElseThrow(BookCopyNotFoundException::new);
        bookCopyRepository.delete(bookCopy);
    }
    public List<BookCopyDTO> showAllBookCopies() {
        List<BookCopy> bookCopyList = bookCopyRepository.findAll();
        return bookCopyMapper.mapToBookCopyDTOList(bookCopyList);
    }
    public void changeBookCopyStatus(final Long bookCopyId, BookCopyStatus status) throws BookCopyNotFoundException {
        BookCopy bookCopy = bookCopyRepository.findById(bookCopyId).orElseThrow(BookCopyNotFoundException::new);
        bookCopy.setStatus(status);
    }
    public long getNbOfAvailBookCopies(String title) {
        return bookCopyRepository.nbOfAvailBookCopies(title);
    }

}
