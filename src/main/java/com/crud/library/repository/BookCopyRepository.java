package com.crud.library.repository;

import com.crud.library.domain.BookCopy;
import com.crud.library.domain.BorrowRecord;
import com.crud.library.domain.dto.BookDto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookCopyRepository extends CrudRepository<BookCopy, Long> {
    @Query
    Long nbOfAvailBookCopies(@Param("title") String title);

    @Override
    List<BookCopy> findAll();
}
