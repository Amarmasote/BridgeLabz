package com.book.bookstore.Service;

import com.book.bookstore.dto.BookDto;
import com.book.bookstore.models.Book;
import com.book.bookstore.models.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public interface BookService {

    public Response bookInsert(Book book);
    public Response bookGet(String bookId);
    public Response bookUpdate(Book book);
    public Response bookRemove(String bookId);
    public Response getAllBooks(int pageNo, int pageSize, String orderBy);
}
