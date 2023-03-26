package com.crud.library.repository;

import com.crud.library.domain.BookCopy;
import com.crud.library.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    @Override
    List<User> findAll();
}
