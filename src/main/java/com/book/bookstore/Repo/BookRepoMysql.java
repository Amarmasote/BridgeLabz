package com.book.bookstore.Repo;

import com.book.bookstore.dto.BookDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepoMysql extends JpaRepository<BookDto, String> {


}
