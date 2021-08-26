package com.book.bookstore.Controller;

import com.book.bookstore.Service.BookService;
import com.book.bookstore.Service.BookServiceImpl;
import com.book.bookstore.dto.BookDto;
import com.book.bookstore.models.Book;
import com.book.bookstore.models.Response;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/book")
@Api(
        value = "These Apis can be used to perform CRUD operations on Book table"
)
@Slf4j
public class BookController {

    @Autowired
    private BookService bookService;

    @ApiOperation(value = "This is the only api to add Book to DB. some fields are mandatory",
            notes = "APi to add book to DataBase",
            response = Response.class)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> insertBook(@RequestBody @Valid Book book, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            log.error("Error in processing request. Request body is {}", book);
            return new ResponseEntity<Response>(new Response(bindingResult.getAllErrors().get(0).getDefaultMessage()
                    ,HttpStatus.UNPROCESSABLE_ENTITY.value(), ""),HttpStatus.UNPROCESSABLE_ENTITY);
        }
        long startTime = System.currentTimeMillis();
        Response response =  bookService.bookInsert(book);
        long endTime = System.currentTimeMillis();

        log.info("Time taken by post book api call - {} ms", (endTime-startTime));
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }

    @ApiOperation(value = "This api is to fetch particular book details", response = Response.class)
    @GetMapping(value = "/{bookId}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully fetched book"),
            @ApiResponse(code = 204, message = "could not find book"),
    })
    public ResponseEntity getBook(@PathVariable @ApiParam(defaultValue = "book001", required = true) String bookId){
        long startTime = System.currentTimeMillis();
        Response response = bookService.bookGet(bookId);
        long endTime = System.currentTimeMillis();

        log.info("Time taken by get book api call - {} ms", (endTime-startTime));
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{bookId}")
    @ApiOperation(value = "Api to delete the book", response = Response.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleted book"),
            @ApiResponse(code = 204, message = "could not find book"),
    })
    public ResponseEntity deleteBook(@PathVariable @ApiParam(defaultValue = "book001", required = true) String bookId){
        long startTime = System.currentTimeMillis();
        Response response =  bookService.bookRemove(bookId);
        long endTime = System.currentTimeMillis();

        log.info("Time taken by delete book api call - {} ms", (endTime-startTime));
        return new ResponseEntity(response, HttpStatus.valueOf(response.getStatus()));
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Api to update the book", response = Response.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated book"),
            @ApiResponse(code = 400, message = "Bad request"),
    })
    public ResponseEntity updateBook(@RequestBody @Valid Book book, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            log.error("Error in processing request. Request body is {}", book);
            return new ResponseEntity<Response>(new Response(bindingResult.getAllErrors().get(0).getDefaultMessage()
                    ,HttpStatus.UNPROCESSABLE_ENTITY.value(), ""),HttpStatus.UNPROCESSABLE_ENTITY);
        }
        long startTime = System.currentTimeMillis();
        Response response =  bookService.bookUpdate(book);
        long endTime = System.currentTimeMillis();

        log.info("Time taken by update book api call - {} ms", (endTime-startTime));
        return new ResponseEntity(response, HttpStatus.valueOf(response.getStatus()));
    }


    @GetMapping(value = "/{pageNo}/{pageSize}")
    public ResponseEntity getAllBooks(@PathVariable int pageNo,
                                      @PathVariable int pageSize,
                                      @RequestParam(required = false) String orderBy) {
        long startTime = System.currentTimeMillis();
        Response response = bookService.getAllBooks(pageNo, pageSize, orderBy);
        long endTime = System.currentTimeMillis();

        log.info("Time taken by getAll book api call - {} ms", (endTime-startTime));
        return new ResponseEntity(response, HttpStatus.valueOf(response.getStatus()));

    }

}
