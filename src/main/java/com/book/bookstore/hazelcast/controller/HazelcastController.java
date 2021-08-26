package com.book.bookstore.hazelcast.controller;

import com.book.bookstore.dto.BookDto;
import com.book.bookstore.hazelcast.service.HazelcastBookService;
import com.book.bookstore.models.Response;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RestController
@Api(value = "This controller class is to do operations on hazelcast data")
@RequestMapping("hazelcast/book")
@Validated
public class HazelcastController {

    @Autowired
    HazelcastBookService hazelcastBookService;

    @PostMapping
    public ResponseEntity putDataInHazelcast(@RequestBody BookDto bookDto){
        Response response = hazelcastBookService.insertIntoHazelcastMap(bookDto);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/{bookId}")
    public ResponseEntity putDataInHazelcast(@PathVariable String bookId){
        Response response = hazelcastBookService.getBookFromHazelcastMap(bookId);
        return ResponseEntity.status(200).body(response);
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity deleteDataInHazelcast(@NotNull @PathVariable String bookId){
        Response response = hazelcastBookService.removeBookFromHazelcastMap(bookId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/mapstat/{mapName}")
    public ResponseEntity GetMapStats(@NotNull @PathVariable String mapName){
        Response response = hazelcastBookService.getMapStats(mapName);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
