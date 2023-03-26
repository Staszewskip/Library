package com.crud.library.repository;


import com.crud.library.domain.BorrowRecord;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BorrowRecordRepository extends CrudRepository<BorrowRecord, Long> {
    @Query
    List<BorrowRecord> findByUserId(@Param("userId") Long userId);

}
