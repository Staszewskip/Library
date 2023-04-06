package com.crud.library.controller;

import com.crud.library.domain.BookCopyStatus;
import com.crud.library.domain.dto.BookCopyDto;
import com.crud.library.domain.dto.BookDto;
import com.crud.library.domain.dto.BorrowRecordDto;
import com.crud.library.domain.dto.UserDto;
import com.crud.library.service.DbService;
import com.crud.library.exception.BookCopyNotFoundException;
import com.crud.library.exception.BookNotFoundException;
import com.crud.library.exception.BorrowRecordNotFoundException;
import com.crud.library.exception.UserNotFoundException;
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

    private final DbService dbService;

    @PostMapping(value = "addUser", consumes = MediaType.APPLICATION_JSON_VALUE) //działa
    public ResponseEntity<Void> addUser(@RequestBody UserDto userDto) {
        dbService.saveUser(userDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "addBook", consumes = MediaType.APPLICATION_JSON_VALUE) //działa
    public ResponseEntity<Void> addBook(@RequestBody BookDto bookDto) {
        dbService.saveBook(bookDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "addBookCopy/{bookId}") //działa
    public ResponseEntity<Void> addBookCopy(@PathVariable Long bookId) throws BookNotFoundException {
        dbService.saveBookCopy(bookId);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "changeBookCopyStatus/{bookCopyId}") // działa
    public ResponseEntity<Void> changeBookCopyStatus(@PathVariable Long bookCopyId, @RequestParam BookCopyStatus status) throws BookCopyNotFoundException {
        dbService.changeBookCopyStatus(bookCopyId, status);
        return ResponseEntity.ok().build();
    }

    @GetMapping() //działa
    public ResponseEntity<List<BookDto>> getBooks() {
        return ResponseEntity.ok(dbService.showAllBooks());
    }

    @GetMapping(value = {"bookCopy"}) //działa
    public ResponseEntity<List<BookCopyDto>> getBookCopy() {
        return ResponseEntity.ok(dbService.showAllBookCopies());
    }

    @GetMapping(value = {"title"}) //działa, książka musi być bez cudzysłowiów
    public ResponseEntity<Long> getNbOfBookCopies(@RequestParam String title) {
        Long nbOfAvailCopies = dbService.getNbOfAvailBookCopies(title);
        return ResponseEntity.ok(nbOfAvailCopies);
    }

    @GetMapping(value = {"users"}) //działa
    public ResponseEntity<List<UserDto>> getUsers() {
        return ResponseEntity.ok(dbService.showAllUsers());
    }

    @PostMapping(value = "borrowBook")//działa
    public ResponseEntity<Void> borrowBook(@RequestParam Long userId, @RequestParam Long bookCopyId) throws UserNotFoundException, BookCopyNotFoundException {
        dbService.borrowBook(userId, bookCopyId);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "borrowsByUser/{userId}") //działa
    public ResponseEntity<List<BorrowRecordDto>> getBorrowedBooksByUser(@PathVariable Long userId) throws UserNotFoundException {
        return ResponseEntity.ok(dbService.showAllBorrowsByUser(userId));
    }

    @PostMapping(value = "returnBook/{borrowRecordId}")//działa
    public ResponseEntity<Void> returnBook(@PathVariable Long borrowRecordId) throws BorrowRecordNotFoundException {
        dbService.returnBook(borrowRecordId);
        return ResponseEntity.ok().build();
    }
}
