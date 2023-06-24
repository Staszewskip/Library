package com.crud.library.controller;

import com.crud.library.domain.dto.BorrowRecordDTO;
import com.crud.library.exception.BookCopyNotFoundException;
import com.crud.library.exception.BorrowRecordNotFoundException;
import com.crud.library.exception.UserNotFoundException;
import com.crud.library.service.BorrowService;
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
@RequestMapping("v1/borrow")
@RequiredArgsConstructor
public class BorrowController {
    private final BorrowService borrowService;

    @Operation(summary = "Borrowing a book copy")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Book copy borrowed sucessfully"),
            @ApiResponse(responseCode = "404",
                    description = "User or book copy not found")
    })
    @PostMapping(value = "borrowBook")
    public ResponseEntity<Void> borrowBook(@RequestParam Long userId, @RequestParam Long bookCopyId) throws UserNotFoundException, BookCopyNotFoundException {
        borrowService.borrowBook(userId, bookCopyId);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Fetching all borrow records of given user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "All borrow records of given user", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "User not found")
    })
    @GetMapping(value = "borrowsByUser/{userId}")
    public ResponseEntity<List<BorrowRecordDTO>> getBorrowedBooksByUser(@PathVariable Long userId) throws UserNotFoundException {
        return ResponseEntity.ok(borrowService.showAllBorrowsByUser(userId));
    }

    @Operation(summary = "Returning a book copy")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Book copy returned successfully"),
            @ApiResponse(responseCode = "404",
                    description = "Borrow record not found")
    })
    @PostMapping(value = "returnBook/{borrowRecordId}")
    public ResponseEntity<Void> returnBook(@PathVariable Long borrowRecordId) throws BorrowRecordNotFoundException {
        borrowService.returnBook(borrowRecordId);
        return ResponseEntity.ok().build();
    }
}
