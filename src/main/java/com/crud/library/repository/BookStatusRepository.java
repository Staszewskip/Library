package com.crud.library.repository;

import com.crud.library.domain.BorrowStatus;
import org.springframework.data.repository.CrudRepository;

public interface BookStatusRepository extends CrudRepository<BorrowStatus, Long> {
}
