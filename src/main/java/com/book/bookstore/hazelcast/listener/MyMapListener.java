package com.book.bookstore.hazelcast.listener;

import com.book.bookstore.dto.BookDto;
import com.hazelcast.core.EntryEvent;
import com.hazelcast.core.MapEvent;
import com.hazelcast.map.listener.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyMapListener implements
        EntryAddedListener<String, BookDto>,
        EntryRemovedListener<String, BookDto>,
        EntryUpdatedListener<String, BookDto>,
        EntryEvictedListener<String, BookDto>,
        EntryLoadedListener<String, BookDto>,
        MapEvictedListener,
        MapClearedListener   {
    @Override
    public void entryAdded(EntryEvent<String, BookDto> entryEvent) {
        System.out.println("Entry added :" + entryEvent.getValue());
        log.info("Entry added event : old value - {}, new value - {} ," ,
                entryEvent.getOldValue(),
                entryEvent.getValue());
    }

    @Override
    public void entryEvicted(EntryEvent<String, BookDto> entryEvent) {
        System.out.println("Entry evicted :" + entryEvent.getValue());
        log.info("Entry evicted event :  evicted value - {} ," , entryEvent.getValue());
    }

    @Override
    public void entryLoaded(EntryEvent<String, BookDto> entryEvent) {

    }

    @Override
    public void entryRemoved(EntryEvent<String, BookDto> entryEvent) {
        System.out.println("Entry removed :" + entryEvent.getValue());
        log.info("Entry removed event :  removed value - {} ," , entryEvent.getValue());
    }

    @Override
    public void entryUpdated(EntryEvent<String, BookDto> entryEvent) {
        System.out.println("Entry updated :" + entryEvent.getValue());
        log.info("Entry updated event : old value - {}, new value - {} ," ,
                entryEvent.getOldValue(),
                entryEvent.getValue());
    }

    @Override
    public void mapCleared(MapEvent mapEvent) {
        System.out.println("map cleared :" + mapEvent.getName());
        log.info("map cleared - {} ," , mapEvent.getName());
    }

    @Override
    public void mapEvicted(MapEvent mapEvent) {
        System.out.println("map evicted :" + mapEvent.getName());
        log.info("map evicted - {} ," , mapEvent.getName());
    }
}
