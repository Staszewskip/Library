package com.crud.library.controller;

import com.crud.library.domain.Book;
import com.crud.library.domain.BookCopy;
import com.crud.library.domain.User;
import com.crud.library.domain.dto.BookCopyDto;
import com.crud.library.domain.dto.BookDto;
import com.crud.library.domain.dto.BorrowRecordDto;
import com.crud.library.domain.dto.UserDto;
import com.crud.library.mapper.LibraryMapper;
import com.crud.library.service.DbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/library")
@RequiredArgsConstructor
public class LibraryController {
    private final LibraryMapper libraryMapper;
    private final DbService dbService;

    @PostMapping(value = "/addUser", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addUser(@RequestBody UserDto userDto) {
        User user = libraryMapper.mapToUser(userDto);
        dbService.saveUser(user);
    }

    @PostMapping(value = "/addBook", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addBook(@RequestBody BookDto bookDto) {
        Book book = libraryMapper.mapToBook(bookDto);
        dbService.saveBook(book);
    }

    @PostMapping(value = "/addBookCopy", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addBookCopy(@RequestBody BookCopyDto bookCopyDto) {
        BookCopy bookCopy = libraryMapper.mapToBookCopy(bookCopyDto);
        dbService.saveBookCopy(bookCopy);
    }

    @PostMapping(value = "/changeBookCopyStatus")
    public void changeBookCopyStatus(@RequestParam BookCopyDto bookCopyDto, @RequestParam String status) {
        BookCopy bookCopy = libraryMapper.mapToBookCopy(bookCopyDto);
        dbService.changeBookCopyStatus(bookCopy, status);
    }

    @GetMapping(value = "/getNbOfBookCopies")
    public long getNbOfBookCopies(@RequestParam BookDto bookDto, @RequestParam String bookCopyStatus) {
        Book bookTitle = libraryMapper.mapToBook(bookDto);
        return dbService.getNbOfCopies(bookTitle,bookCopyStatus);
    }

    @PostMapping(value = "/borrowBook")
    public void borrowBook(@RequestParam BookCopyDto bookCopyId, @RequestParam UserDto userId) {
        dbService.borrowBook(libraryMapper.mapToUser(userId), libraryMapper.mapToBookCopy(bookCopyId));
    }

    @PostMapping(value = "/returnBook")
    public void returnBook(@RequestParam BorrowRecordDto borrowRecordDto) {
        dbService.returnBook(libraryMapper.mapToBorrowRecord(borrowRecordDto));
    }
}
