package com.crud.library.controller;

import com.crud.library.domain.BookCopyStatus;
import com.crud.library.domain.dto.BookCopyDTO;
import com.crud.library.exception.BookCopyNotFoundException;
import com.crud.library.exception.BookNotFoundException;
import com.crud.library.service.BookCopyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("v1/bookcopy")
@RequiredArgsConstructor
public class BookCopyController {
    private final BookCopyService bookCopyService;

    @Operation(summary = "Adding new book copy of existing book")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Book copy added successfully", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Book not found")
    })
    @PostMapping(value = "addBookCopy/{bookId}")
    public ResponseEntity<Void> addBookCopy(@PathVariable Long bookId) throws BookNotFoundException {
        bookCopyService.saveBookCopy(bookId);
        return ResponseEntity.ok().build();
    }
    @Operation(summary = "Changing status of the book copy")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Book copy status changed successfully", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Book copy not found")
    })
    @PostMapping(value = "changeBookCopyStatus/{bookCopyId}")
    public ResponseEntity<Void> changeBookCopyStatus(@PathVariable Long bookCopyId, @RequestParam BookCopyStatus status) throws BookCopyNotFoundException {
        bookCopyService.changeBookCopyStatus(bookCopyId, status);
        return ResponseEntity.ok().build();
    }
    @Operation(summary = "Fetching all book copies")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "All book copies from database", content = {@Content(mediaType = "application/json")}),
    })
    @GetMapping(value = {"bookCopy"})
    public ResponseEntity<List<BookCopyDTO>> getBookCopy() {
        return ResponseEntity.ok(bookCopyService.showAllBookCopies());
    }

    @Operation(summary = "Fetching number of available book copies (based on the title)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "All available book copies of given title", content = {@Content(mediaType = "application/json")}),
    })
    @GetMapping(value = {"title"})
    public ResponseEntity<Long> getNbOfBookCopies(@RequestParam String title) {
        Long nbOfAvailCopies = bookCopyService.getNbOfAvailBookCopies(title);
        return ResponseEntity.ok(nbOfAvailCopies);
    }
    @Operation(summary = "Deleting a book copy")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Book copy deleted successfully"),
            @ApiResponse(responseCode = "404",
                    description = "Book copy not found")
    })
    @DeleteMapping(value = "bookCopy/{bookCopyId}")
    public ResponseEntity<Void> deleteBookCopy(@PathVariable Long bookCopyId) throws BookCopyNotFoundException {
        bookCopyService.deleteBookCopy(bookCopyId);
        return ResponseEntity.ok().build();
    }
}
