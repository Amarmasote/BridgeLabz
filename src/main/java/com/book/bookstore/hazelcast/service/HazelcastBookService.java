package com.book.bookstore.hazelcast.service;

import com.book.bookstore.dto.BookDto;
import com.book.bookstore.hazelcast.listener.MyMapListener;
import com.book.bookstore.models.Book;
import com.book.bookstore.models.Response;
import com.hazelcast.config.Config;
import com.hazelcast.config.EvictionPolicy;
import com.hazelcast.config.MapConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.hazelcast.monitor.LocalMapStats;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Component
public class HazelcastBookService {
    IMap<String, BookDto> bookMap;
    HazelcastInstance hazelcastInstance;

    @PostConstruct
    public void hazelcastInit(){
        Config config = new Config();
        MapConfig mapConfig = new MapConfig();
        mapConfig.setName("book")
                .setTimeToLiveSeconds(5)
                .setEvictionPolicy(EvictionPolicy.LRU);

        config.addMapConfig(mapConfig);
        hazelcastInstance = Hazelcast.newHazelcastInstance();
        bookMap = hazelcastInstance.getMap("book");
        bookMap.addEntryListener(new MyMapListener(), true);

    }

    public Response insertIntoHazelcastMap(BookDto bookDto){
        Optional<BookDto> savedBook = Optional.ofNullable(bookMap.put(bookDto.getBookId(), bookDto));
        if(savedBook.isPresent()){
            return Response.builder().body(savedBook)
                    .message("Book updated in hazelcast map")
                    .status(200)
                    .build();
        }
        else{
            return Response.builder().body(bookDto)
                    .message("Book saved in hazelcast map")
                    .status(200)
                    .build();
        }
        }


    public Response getBookFromHazelcastMap(String bookId){
        Optional<BookDto> savedBook = Optional.ofNullable(bookMap.get(bookId));
        if(savedBook.isPresent()){
            return Response.builder().body(savedBook)
                    .message("Book is present in hazelcast map")
                    .status(200)
                    .build();
        }
        else{
            return Response.builder()
                    .message("Book is not there in hazelcast")
                    .status(204)
                    .build();
        }
    }

    public Response removeBookFromHazelcastMap(String bookId){
        Optional<BookDto> deletedBook = Optional.ofNullable(bookMap.remove(bookId));
        if(deletedBook.isPresent()){
            return Response.builder().body(deletedBook)
                    .message("Book removed from hazelcast map")
                    .status(200)
                    .build();
        }
        else{
            return Response.builder()
                    .message("Book is not there in hazelcast to delete")
                    .status(204)
                    .build();
        }
    }

    public Response getMapStats(String mapName){
        LocalMapStats localMapStats = hazelcastInstance.getMap(mapName).getLocalMapStats();
        return Response.builder().body(localMapStats)
                                .status(200)
                                .message("Map stats are as follow")
                                .build();
    }
}
