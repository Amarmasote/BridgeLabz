package com.book.bookstore.Service;

import com.book.bookstore.dto.BookDto;
import com.book.bookstore.models.Book;
import com.book.bookstore.models.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public interface BookService {

    public ResponseEntity<Response> bookInsert(Book book);
    public ResponseEntity<Response> bookGet(String bookId);
    public ResponseEntity bookUpdate(Book book);
    public ResponseEntity bookRemove(String bookId);
}
