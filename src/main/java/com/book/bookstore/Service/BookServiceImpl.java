package com.book.bookstore.Service;


import com.book.bookstore.Repo.BookRepoMysql;
import com.book.bookstore.dto.BookDto;
import com.book.bookstore.exception.BookNotFoundException;
import com.book.bookstore.models.Book;
import com.book.bookstore.models.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class BookServiceImpl implements BookService{

    @Autowired
    private BookRepoMysql bookRepoMysql;

    @Override
    public Response bookInsert(Book book) {
        Response response;
        BookDto bookDto = new BookDto();
        BeanUtils.copyProperties(book, bookDto);
        Optional<BookDto> savedBook = Optional.of(bookRepoMysql.save(bookDto));
        // using optional
        if (savedBook.isPresent()){
            log.trace("requested book is added to DB");
            response = Response.builder().message("Book Successfully inserted")
                                .status(HttpStatus.CREATED.value())
                                .body(savedBook)
                                .build();
            return response;
        }
        else{
            log.error("Book can not be saved to DB");
            response = Response.builder().message("Book can not be saved to DB - Bad Request")
                    .status(HttpStatus.PARTIAL_CONTENT.value())
                    .body(savedBook)
                    .build();
            return response;
        }
    }

    @Override
    public Response bookGet(String bookId) {

        BookDto fetchedBook = bookRepoMysql.findById(bookId).orElseThrow(() -> new BookNotFoundException("Not found.!"));
        log.info("Requsted book is found - fetching");
        return Response.builder()
                .body(fetchedBook)
                .message("Book data fetched successfully")
                .status(200)
                .build();
    }

    @Override
    public Response bookUpdate(Book book) {
        Response response = new Response();
        BookDto bookDto = new BookDto();
        BeanUtils.copyProperties(book, bookDto);
        BookDto savedBook = bookRepoMysql.save(bookDto);
        if (savedBook != null){
            log.info("Book is updated successfully");
            response.setBody(savedBook);
            response.setMessage("Book is updated");
            response.setStatus(200);
            return response;
        }
        else{
            log.error("Book is not updated");
            throw new BookNotFoundException("Check out your input");
        }
    }

    @Override
    public Response bookRemove(String bookId) {
         bookRepoMysql.deleteById(bookId);
        log.info("Book is removed successfully");
         return Response.builder()
                         .status(200)
                         .message("book data deleted successfully")
                        .build();
    }

    @Override
    public Response getAllBooks(int pageNo, int pageSize, String orderBy) {
        if(orderBy == null){
            log.info("OrderBy is null, so order is not maintained");
            Pageable pageableRequest = PageRequest.of(pageNo, pageSize);
        }
        else{
            log.info("OrderBy is {}, so so books are orderedBy - {}", orderBy);
            Pageable pageableRequest = PageRequest.of(pageNo, pageSize, Sort.by(orderBy));
        }

        List<BookDto> PagedBooks = bookRepoMysql.findAll();

        return Response.builder().message("Books available in requested page")
                        .status(200)
                        .body(PagedBooks)
                        .build();
    }
}
