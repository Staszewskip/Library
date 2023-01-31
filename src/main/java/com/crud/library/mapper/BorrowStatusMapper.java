package com.crud.library.mapper;

import com.crud.library.domain.BorrowStatus;
import com.crud.library.domain.dto.BorrowStatusDto;

public class BorrowStatusMapper {
    public BorrowStatus mapToBorrowStatus(final BorrowStatusDto borrowStatusDto) {
        return new BorrowStatus(
                borrowStatusDto.getBorrowId(),
                borrowStatusDto.getUser(),
                borrowStatusDto.getBookCopy(),
                borrowStatusDto.getBorrowDate()
        );
    }

    public BorrowStatusDto mapToBorrowStatusDto(final BorrowStatus borrowStatus) {
        return new BorrowStatusDto(
                borrowStatus.getBorrowId(),
                borrowStatus.getUser(),
                borrowStatus.getBookCopy(),
                borrowStatus.getBorrowDate()
        );
    }
}
