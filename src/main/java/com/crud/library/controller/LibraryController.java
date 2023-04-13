package com.crud.library.controller;

import com.crud.library.domain.BookCopyStatus;
import com.crud.library.domain.dto.BookCopyDTO;
import com.crud.library.domain.dto.BookDTO;
import com.crud.library.domain.dto.BorrowRecordDTO;
import com.crud.library.domain.dto.UserDTO;
import com.crud.library.service.DbService;
import com.crud.library.exception.BookCopyNotFoundException;
import com.crud.library.exception.BookNotFoundException;
import com.crud.library.exception.BorrowRecordNotFoundException;
import com.crud.library.exception.UserNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Adding new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "User added successfully"),
            @ApiResponse(responseCode = "500",
                    description = "Server error - please read logs")
    })
    @PostMapping(value = "addUser", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addUser(@RequestBody UserDTO userDTO) {
        dbService.saveUser(userDTO);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Adding new book")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Book added successfully"),
            @ApiResponse(responseCode = "500",
                    description = "Server error - please read logs")
    })
    @PostMapping(value = "addBook", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addBook(@RequestBody BookDTO bookDTO) {
        dbService.saveBook(bookDTO);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Adding new book copy of existing book")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Book copy added successfully"),
            @ApiResponse(responseCode = "500",
                    description = "Server error - please read logs")
    })
    @PostMapping(value = "addBookCopy/{bookId}")
    public ResponseEntity<Void> addBookCopy(@PathVariable Long bookId) throws BookNotFoundException {
        dbService.saveBookCopy(bookId);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Changing status of the book copy")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Book copy status changed successfully"),
            @ApiResponse(responseCode = "500",
                    description = "Server error - please read logs")
    })
    @PostMapping(value = "changeBookCopyStatus/{bookCopyId}")
    public ResponseEntity<Void> changeBookCopyStatus(@PathVariable Long bookCopyId, @RequestParam BookCopyStatus status) throws BookCopyNotFoundException {
        dbService.changeBookCopyStatus(bookCopyId, status);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Fetching all books")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "All books from database", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500",
                    description = "Server error - please read logs")
    })
    @GetMapping() //działa
    public ResponseEntity<List<BookDTO>> getBooks() {
        return ResponseEntity.ok(dbService.showAllBooks());
    }

    @Operation(summary = "Fetching all book copies")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "All book copies from database", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500",
                    description = "Server error - please read logs")
    })
    @GetMapping(value = {"bookCopy"})
    public ResponseEntity<List<BookCopyDTO>> getBookCopy() {
        return ResponseEntity.ok(dbService.showAllBookCopies());
    }

    @Operation(summary = "Fetching number of available book copies (based on the title)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "All available book copies of given title"),
            @ApiResponse(responseCode = "500",
                    description = "Server error - please read logs")
    })
    @GetMapping(value = {"title"})
    public ResponseEntity<Long> getNbOfBookCopies(@RequestParam String title) {
        Long nbOfAvailCopies = dbService.getNbOfAvailBookCopies(title);
        return ResponseEntity.ok(nbOfAvailCopies);
    }

    @Operation(summary = "Fetching all users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "All users from database", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500",
                    description = "Server error - please read logs")
    })
    @GetMapping(value = {"users"})
    public ResponseEntity<List<UserDTO>> getUsers() {
        return ResponseEntity.ok(dbService.showAllUsers());
    }

    @Operation(summary = "Borrowing a book copy")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Book copy borrowed sucessfully"),
            @ApiResponse(responseCode = "500",
                    description = "Server error - please read logs")
    })
    @PostMapping(value = "borrowBook")
    public ResponseEntity<Void> borrowBook(@RequestParam Long userId, @RequestParam Long bookCopyId) throws UserNotFoundException, BookCopyNotFoundException {
        dbService.borrowBook(userId, bookCopyId);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Fetching all borrow records of given user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "All borrow records of given user"),
            @ApiResponse(responseCode = "500",
                    description = "Server error - please read logs")
    })
    @GetMapping(value = "borrowsByUser/{userId}")
    public ResponseEntity<List<BorrowRecordDTO>> getBorrowedBooksByUser(@PathVariable Long userId) throws UserNotFoundException {
        return ResponseEntity.ok(dbService.showAllBorrowsByUser(userId));
    }

    @Operation(summary = "Returning a book copy")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Book copy returned successfully"),
            @ApiResponse(responseCode = "500",
                    description = "Server error - please read logs")
    })
    @PostMapping(value = "returnBook/{borrowRecordId}")
    public ResponseEntity<Void> returnBook(@PathVariable Long borrowRecordId) throws BorrowRecordNotFoundException {
        dbService.returnBook(borrowRecordId);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Deleting a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "User deleted successfully"),
            @ApiResponse(responseCode = "500",
                    description = "Server error - please read logs")
    })
    @DeleteMapping(value = "user/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) throws UserNotFoundException {
        dbService.deleteUser(userId);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Deleting a book")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Book deleted successfully"),
            @ApiResponse(responseCode = "500",
                    description = "Server error - please read logs")
    })
    @DeleteMapping(value = "book/{bookId}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long bookId) throws BookNotFoundException {
        dbService.deleteBook(bookId);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Deleting a book copy")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Book copy deleted successfully"),
            @ApiResponse(responseCode = "500",
                    description = "Server error - please read logs")
    })
    @DeleteMapping(value = "bookCopy/{bookCopyId}")
    public ResponseEntity<Void> deleteBookCopy(@PathVariable Long bookCopyId) throws BookCopyNotFoundException {
        dbService.deleteBookCopy(bookCopyId);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Updating an user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "User updated successfully"),
            @ApiResponse(responseCode = "500",
                    description = "Server error - please read logs")
    })
    @PutMapping(value = "user", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO) throws UserNotFoundException {
        return ResponseEntity.ok(dbService.updateUser(userDTO));
    }

    @Operation(summary = "Updating a book")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Book updated successfully"),
            @ApiResponse(responseCode = "500",
                    description = "Server error - please read logs")
    })
    @PutMapping(value = "book", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookDTO> updateBook(@RequestBody BookDTO bookDTO) throws BookNotFoundException {
        return ResponseEntity.ok(dbService.updateBook(bookDTO));
    }
}
