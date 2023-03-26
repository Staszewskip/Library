package com.crud.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@AllArgsConstructor
@Getter
public enum BookCopyStatus {
    AVAILABLE("AVAILABLE"),
    RENTED("RENTED"),
    DAMAGED("DAMAGED"),
    LOST("LOST");
    private final String status;

    private static Map<BookCopyStatus, String> bookCopyStatuses = Map.of(
            BookCopyStatus.AVAILABLE, BookCopyStatus.AVAILABLE.getStatus(),
            BookCopyStatus.RENTED, BookCopyStatus.RENTED.getStatus(),
            BookCopyStatus.DAMAGED, BookCopyStatus.DAMAGED.getStatus(),
            BookCopyStatus.LOST, BookCopyStatus.LOST.getStatus()
    );
}
