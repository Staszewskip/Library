package com.crud.library.repository;

import com.crud.library.domain.Book;
import com.crud.library.domain.BookCopy;
import org.springframework.data.repository.CrudRepository;

public interface BookCopyRepository extends CrudRepository<BookCopy, Long> {

    public int getNbOfBookCopies(Book bookTitle);
}
