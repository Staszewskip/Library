package com.crud.library.controller;

import com.crud.library.domain.Book;
import com.crud.library.domain.BookCopy;
import com.crud.library.domain.BorrowRecord;
import com.crud.library.domain.User;
import com.crud.library.domain.dto.BookCopyDto;
import com.crud.library.domain.dto.BookDto;
import com.crud.library.domain.dto.BorrowRecordDto;
import com.crud.library.domain.dto.UserDto;
import com.crud.library.mapper.LibraryMapper;
import com.crud.library.service.DbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("v1/library")
@RequiredArgsConstructor
public class LibraryController {
    private final LibraryMapper libraryMapper;
    private final DbService dbService;

    @PostMapping(value = "addUser", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addUser(@RequestBody UserDto userDto) {
        User user = libraryMapper.mapToUser(userDto);
        dbService.saveUser(user);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "addBook", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addBook(@RequestBody BookDto bookDto) {
        Book book = libraryMapper.mapToBook(bookDto);
        dbService.saveBook(book);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "addBookCopy", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addBookCopy(@RequestBody BookCopyDto bookCopyDto) {
        BookCopy bookCopy = libraryMapper.mapToBookCopy(bookCopyDto);
        dbService.saveBookCopy(bookCopy);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "changeBookCopyStatus")
    public ResponseEntity<Void>changeBookCopyStatus(@RequestParam BookCopyDto bookCopyDto, @RequestParam String status) {
        BookCopy bookCopy = libraryMapper.mapToBookCopy(bookCopyDto);
        dbService.changeBookCopyStatus(bookCopy, status);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "getNbOfBookCopies")
    public ResponseEntity<Long> getNbOfBookCopies(@RequestParam BookDto bookDto, @RequestParam String bookCopyStatus) {
        Book book = libraryMapper.mapToBook(bookDto);
        return ResponseEntity.ok(dbService.getNbOfCopies(book,bookCopyStatus));
    }

    @PostMapping(value = "borrowBook")
    public ResponseEntity<Void> borrowBook(@RequestParam BookCopyDto bookCopyId, @RequestParam UserDto userId) {
        dbService.borrowBook(libraryMapper.mapToUser(userId), libraryMapper.mapToBookCopy(bookCopyId));
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "returnBook")
    public ResponseEntity<Void> returnBook(@RequestParam BorrowRecordDto borrowRecordDto) {
             dbService.returnBook(libraryMapper.mapToBorrowRecord(borrowRecordDto));
        return ResponseEntity.ok().build();
    }
}
