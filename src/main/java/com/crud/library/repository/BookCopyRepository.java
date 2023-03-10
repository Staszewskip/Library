package com.crud.library.repository;

import com.crud.library.domain.BookCopy;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface BookCopyRepository extends CrudRepository<BookCopy, Long> {
    @Query
    Long nbOfAvailBookCopies(@Param("title") String title);

}
