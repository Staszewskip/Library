package com.crud.library.service;

import com.crud.library.domain.Book;
import com.crud.library.domain.dto.BookDTO;
import com.crud.library.exception.BookNotFoundException;
import com.crud.library.mapper.LibraryMapper;
import com.crud.library.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final LibraryMapper libraryMapper;
    public void saveBook(BookDTO bookDTO) {
        Book book = libraryMapper.mapToBook(bookDTO);
        bookRepository.save(book);
    }

    public List<BookDTO> showAllBooks() {
        List<Book> bookList = bookRepository.findAll();
        return libraryMapper.mapToBookDTOList(bookList);
    }
    public void deleteBook(Long bookId) throws BookNotFoundException {
        Book book = bookRepository.findById(bookId).orElseThrow(BookNotFoundException::new);
        bookRepository.delete(book);
    }
    public BookDTO updateBook(final BookDTO bookDTO) throws BookNotFoundException {
        Book book = bookRepository.findById(bookDTO.getBookId()).orElseThrow(BookNotFoundException::new);
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());
        book.setYear(bookDTO.getYear());
        bookRepository.save(book);
        return libraryMapper.mapToBookDTO(book);
    }
}
