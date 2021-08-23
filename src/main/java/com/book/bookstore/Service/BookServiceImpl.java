package com.book.bookstore.Service;


import com.book.bookstore.Repo.BookRepoMysql;
import com.book.bookstore.dto.BookDto;
import com.book.bookstore.exception.BookNotFoundException;
import com.book.bookstore.models.Book;
import com.book.bookstore.models.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BookServiceImpl implements BookService{

    @Autowired
    private BookRepoMysql bookRepoMysql;

    @Override
    public ResponseEntity<Response> bookInsert(Book book) {
        Response response;
        BookDto bookDto = new BookDto();
        BeanUtils.copyProperties(book, bookDto);
        Optional<BookDto> savedBook = Optional.of(bookRepoMysql.save(bookDto));
        // using optional
        if (savedBook.isPresent()){
            response = Response.builder().message("Book Successfully inserted")
                                .status(HttpStatus.CREATED.value())
                                .body(savedBook)
                                .build();
            return ResponseEntity.status(200).body(response);
        }
        else{
            response = Response.builder().message("Book can not be saved to DB - Bad Request")
                    .status(HttpStatus.PARTIAL_CONTENT.value())
                    .body(savedBook)
                    .build();
            return ResponseEntity.status(400).body(response);
        }
    }

    @Override
    public ResponseEntity<Response> bookGet(String bookId) {

        BookDto fetchedBook = bookRepoMysql.findById(bookId).orElseThrow(() -> new BookNotFoundException("Not found.!"));
        return ResponseEntity.status(200).body(Response.builder()
                .body(fetchedBook)
                .message("Book data fetched successfully")
                .status(200)
                .build());
    }

    @Override
    public ResponseEntity bookUpdate(Book book) {
        ResponseEntity responseEntity;
        BookDto bookDto = new BookDto();
        BeanUtils.copyProperties(book, bookDto);
        BookDto savedBook = bookRepoMysql.save(bookDto);
        if (savedBook != null){
            responseEntity = new ResponseEntity(savedBook, HttpStatus.FOUND);
            return responseEntity;
        }
        else{
            throw new BookNotFoundException("Check out your input");
        }
    }

    @Override
    public ResponseEntity bookRemove(String bookId) {
         bookRepoMysql.deleteById(bookId);

         return ResponseEntity.status(HttpStatus.OK)
                 .body(Response.builder()
                         .status(200)
                         .message("book data deleted successfully"));
    }
}
