package com.crud.library.controller;

import com.crud.library.domain.dto.BookDTO;
import com.crud.library.exception.BookNotFoundException;
import com.crud.library.service.BookService;
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
@RequestMapping("v1/book")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;
    @Operation(summary = "Adding new book")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Book added successfully", content = {@Content(mediaType = "application/json")}),
    })
    @PostMapping(value = "addBook", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addBook(@RequestBody BookDTO bookDTO) {
        bookService.saveBook(bookDTO);
        return ResponseEntity.ok().build();
    }
    @Operation(summary = "Fetching all books")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "All books from database", content = {@Content(mediaType = "application/json")}),
    })
    @GetMapping()
    public ResponseEntity<List<BookDTO>> getBooks() {
        return ResponseEntity.ok(bookService.showAllBooks());
    }
    @Operation(summary = "Deleting a book")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Book deleted successfully"),
            @ApiResponse(responseCode = "404",
                    description = "Book not found")
    })
    @DeleteMapping(value = "book/{bookId}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long bookId) throws BookNotFoundException {
        bookService.deleteBook(bookId);
        return ResponseEntity.ok().build();
    }
    @Operation(summary = "Updating a book. Only author, title, year can be updated")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Book updated successfully", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Book not found")
    })
    @PutMapping(value = "book", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookDTO> updateBook(@RequestBody BookDTO bookDTO) throws BookNotFoundException {
        return ResponseEntity.ok(bookService.updateBook(bookDTO));
    }
}
