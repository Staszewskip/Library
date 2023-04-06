package com.crud.library.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalHttpErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<Object> handleBookNotFoundException(BookNotFoundException bookNotFoundException) {
        return new ResponseEntity<>("Book with given id not found", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BookCopyNotFoundException.class)
    public ResponseEntity<Object> handleBookCopyNotFoundException(BookCopyNotFoundException bookCopyNotFoundException) {
        return new ResponseEntity<>("BookCopy with given id not found", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException userNotFoundException) {
        return new ResponseEntity<>("User with given id not found", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BorrowRecordNotFoundException.class)
    public ResponseEntity<Object> handleBorrowRecordFoundException(BorrowRecordNotFoundException borrowRecordFoundException) {
        return new ResponseEntity<>("Borrow record with given id not found", HttpStatus.BAD_REQUEST);
    }
}
