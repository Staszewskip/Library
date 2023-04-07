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

    @PostMapping(value = "addUser", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addUser(@RequestBody UserDto userDto) {
        dbService.saveUser(userDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "addBook", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addBook(@RequestBody BookDto bookDto) {
        dbService.saveBook(bookDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "addBookCopy/{bookId}")
    public ResponseEntity<Void> addBookCopy(@PathVariable Long bookId) throws BookNotFoundException {
        dbService.saveBookCopy(bookId);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "changeBookCopyStatus/{bookCopyId}")
    public ResponseEntity<Void> changeBookCopyStatus(@PathVariable Long bookCopyId, @RequestParam BookCopyStatus status) throws BookCopyNotFoundException {
        dbService.changeBookCopyStatus(bookCopyId, status);
        return ResponseEntity.ok().build();
    }

    @GetMapping() //działa
    public ResponseEntity<List<BookDto>> getBooks() {
        return ResponseEntity.ok(dbService.showAllBooks());
    }

    @GetMapping(value = {"bookCopy"})
    public ResponseEntity<List<BookCopyDto>> getBookCopy() {
        return ResponseEntity.ok(dbService.showAllBookCopies());
    }

    @GetMapping(value = {"title"})
    public ResponseEntity<Long> getNbOfBookCopies(@RequestParam String title) {
        Long nbOfAvailCopies = dbService.getNbOfAvailBookCopies(title);
        return ResponseEntity.ok(nbOfAvailCopies);
    }

    @GetMapping(value = {"users"})
    public ResponseEntity<List<UserDto>> getUsers() {
        return ResponseEntity.ok(dbService.showAllUsers());
    }

    @PostMapping(value = "borrowBook")
    public ResponseEntity<Void> borrowBook(@RequestParam Long userId, @RequestParam Long bookCopyId) throws UserNotFoundException, BookCopyNotFoundException {
        dbService.borrowBook(userId, bookCopyId);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "borrowsByUser/{userId}")
    public ResponseEntity<List<BorrowRecordDto>> getBorrowedBooksByUser(@PathVariable Long userId) throws UserNotFoundException {
        return ResponseEntity.ok(dbService.showAllBorrowsByUser(userId));
    }

    @PostMapping(value = "returnBook/{borrowRecordId}")
    public ResponseEntity<Void> returnBook(@PathVariable Long borrowRecordId) throws BorrowRecordNotFoundException {
        dbService.returnBook(borrowRecordId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "user/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) throws UserNotFoundException {
        dbService.deleteUser(userId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "book/{bookId}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long bookId) throws BookNotFoundException {
        dbService.deleteBook(bookId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "bookCopy/{bookCopyId}")
    public ResponseEntity<Void> deleteBookCopy(@PathVariable Long bookCopyId) throws BookCopyNotFoundException {
        dbService.deleteBookCopy(bookCopyId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "borrowRecord/{borrowRecordId}")
    public ResponseEntity<Void> deleteBorrowRecord(@PathVariable Long borrowRecordId) throws BorrowRecordNotFoundException {
        dbService.deleteBorrowRecord(borrowRecordId);
        return ResponseEntity.ok().build();
    }
    @PutMapping(value = "user", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto) throws UserNotFoundException {
        return ResponseEntity.ok(dbService.updateUser(userDto));
    }
    @PutMapping(value = "book", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookDto> updateBook(@RequestBody BookDto bookDto) throws BookNotFoundException {
        return ResponseEntity.ok(dbService.updateBook(bookDto));
    }
}
