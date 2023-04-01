package com.crud.library.repository;


import com.crud.library.domain.BorrowRecord;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BorrowRecordRepository extends CrudRepository<BorrowRecord, Long> {
    @Query
    Optional<List<BorrowRecord>> findByUserId(@Param("userId") Long userId);

}
