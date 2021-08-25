package com.book.bookstore.Controller;

import com.book.bookstore.Service.BookService;
import com.book.bookstore.Service.BookServiceImpl;
import com.book.bookstore.dto.BookDto;
import com.book.bookstore.models.Book;
import com.book.bookstore.models.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
@Api(
        value = "These Apis can be used to perform CRUD operations on Book table"
)
public class BookController {

    @Autowired
    private BookService bookService;

    @ApiOperation(value = "This is the only api to add Book to DB. some fields are mandatory", notes = "APi to add book to DataBase")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> insertBook(@RequestBody Book book){
        return bookService.bookInsert(book);
    }

    @ApiOperation(value = "This api si to fetch perticular book details")
    @GetMapping(value = "/{bookId}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully fetched book"),
            @ApiResponse(code = 204, message = "could not find book"),
    })
    public ResponseEntity getBook(@PathVariable String bookId){
        ResponseEntity<Response> bookDto = bookService.bookGet(bookId);
        return new ResponseEntity(Response.builder().body(bookDto).status(200).message("Fetched successfully")
                , HttpStatus.OK);
    }

    @DeleteMapping(value = "/{bookId}")
    public ResponseEntity deleteBook(@PathVariable String bookId){
        return bookService.bookRemove(bookId);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateBook(@RequestBody Book book){
        return bookService.bookUpdate(book);
    }
}
