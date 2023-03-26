package com.crud.library.controller;

import com.crud.library.domain.*;
import com.crud.library.domain.dto.BookCopyDto;
import com.crud.library.domain.dto.BookDto;
import com.crud.library.domain.dto.BorrowRecordDto;
import com.crud.library.domain.dto.UserDto;
import com.crud.library.mapper.LibraryMapper;
import com.crud.library.service.DbService;
import exception.BookCopyNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("v1/library")
@RequiredArgsConstructor
public class LibraryController {
    private final LibraryMapper libraryMapper;
    private final DbService dbService;

    @PostMapping(value = "addUser", consumes = MediaType.APPLICATION_JSON_VALUE) //działa
    public ResponseEntity<Void> addUser(@RequestBody UserDto userDto) {
        User user = libraryMapper.mapToUser(userDto);
        dbService.saveUser(user);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "addBook", consumes = MediaType.APPLICATION_JSON_VALUE) //działa
    public ResponseEntity<Void> addBook(@RequestBody BookDto bookDto) {
        Book book = libraryMapper.mapToBook(bookDto);
        dbService.saveBook(book);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "addBookCopy/{bookId}") // działa, ale nie pozwala dodać kilku kopii do jednej książki
    public ResponseEntity<Void> addBookCopy(@PathVariable Long bookId) {
        dbService.saveBookCopy(bookId);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "changeBookCopyStatus/{bookCopyId}") //nie rozpoznaje statusu z enuma
    public ResponseEntity<Void> changeBookCopyStatus(@PathVariable Long bookCopyId, @RequestBody BookCopyStatus status) throws BookCopyNotFoundException {
        dbService.changeBookCopyStatus(bookCopyId, status);
        return ResponseEntity.ok().build();
    }

    @GetMapping() //działa, ale nie ma linku z listą BookCopy
    public ResponseEntity<List<BookDto>> getBooks() {
        List<Book> bookList = dbService.showAllBooks();
        return ResponseEntity.ok(libraryMapper.mapToBookDtoList(bookList));
    }

    @GetMapping(value = {"bookCopy"}) //działa
    public ResponseEntity<List<BookCopyDto>> getBookCopy() {
        List<BookCopy> bookCopyList = dbService.showAllBookCopies();
        return ResponseEntity.ok(libraryMapper.mapToBookCopyDtoList(bookCopyList));
    }

    @GetMapping(value = {"title"}) //nie odczytuje Stringa z pola w enummie
    public ResponseEntity<Long> getNbOfBookCopies(@RequestParam String title) {
        Long nbOfAvailCopies = dbService.getNbOfAvailBookCopies(title);
        return ResponseEntity.ok(nbOfAvailCopies);
    }

    @GetMapping(value = {"users"}) //działa
    public ResponseEntity<List<UserDto>> getUsers() {
        List<User> userList = dbService.showAllUsers();
        return ResponseEntity.ok(libraryMapper.mapToUserDtoList(userList));
    }

    @PostMapping(value = "borrowBook")//czy Optional jest dobrze? Jakaś obsługa błędów?
    public ResponseEntity<Void> borrowBook(@RequestParam Long borrowRecordId, @RequestParam Long userId, @RequestParam Long bookCopyId) {
        dbService.borrowBook(borrowRecordId, userId, bookCopyId);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "borrowsByUser/{userId}") //działa, ale książki mają zawsze status available
    public ResponseEntity<List<BorrowRecordDto>> getBorrowedBooksByUser(@PathVariable Long userId) {
        List<BorrowRecord> borrowRecordList = dbService.showAllBorrowsByUser(userId);
        return ResponseEntity.ok(libraryMapper.mapToBorrowRecordDtoList(borrowRecordList));
    }

    @PostMapping(value = "returnBook/{borrowRecordId}")
    //działa - dodaje się data zwrotu, ale nie zmienia się status na avaible
    public ResponseEntity<Void> returnBook(@PathVariable Long borrowRecordId) {
        dbService.returnBook(borrowRecordId);
        return ResponseEntity.ok().build();
    }
}
