package com.bridgelabz.problems.service;

import com.bridgelabz.problems.model.Address;
import com.bridgelabz.problems.model.RepoResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Component
@Slf4j
public class AddressService {
    Map<String, Address> addressBook;

    @PostConstruct
    public void initialiseAddressBook(){
        log.info("address book initialisation started..!");
        addressBook = new HashMap<>();

        log.info("address book initialisation completed..!");
    }

    public RepoResponse addToContactBook(Address address){
        addressBook.put(address.getFirstName() + "|" + address.getLastName(), address);
        RepoResponse repoResponse = RepoResponse.builder().message("Contact added to Book")
                                                          .status(200)
                                                           .responseBody(address)
                                                            .build();
        return repoResponse;
    }

    public RepoResponse updateContactBook(Address address){
        String key = address.getFirstName() + "|" + address.getLastName();
        RepoResponse repoResponse;

        if(addressBook.containsKey(key)){
            log.info("Updating the contact info...!");
            repoResponse = RepoResponse.builder().message("Contact Updated in Book")
                    .status(200)
                    .responseBody(address)
                    .build();
        }
        else {
            log.info("creating the contact info...!");
            repoResponse = RepoResponse.builder().message("Contact added to Book")
                    .status(200)
                    .responseBody(address)
                    .build();
        }
        addressBook.put(address.getFirstName() + "|" + address.getLastName(), address);

        return repoResponse;
    }

    public RepoResponse removeFromContactBook(String firstName, String lastName){
        addressBook.remove(firstName + "|" + lastName);
        RepoResponse repoResponse = RepoResponse.builder().message("Contact removed from  book")
                .status(200)
                .responseBody(null)
                .build();
        return repoResponse;
    }

    public RepoResponse getFromContactBook(String firstName, String lastName){
        // making this optional bcz, addressBook may not have it
        Optional<Address> address = Optional.ofNullable(addressBook.get(firstName + "|" + lastName));
        RepoResponse repoResponse = RepoResponse.builder().message("Contact fetched from  book")
                .status(200)
                .responseBody(address)
                .build();
        return repoResponse;
    }

    public int findNumberOfContactFromACity(String city){
        return (int) addressBook.values().stream().filter(address -> address.getCity().equals(city))
                                     .count();
    }

    public int findNumberOfContactFromAState(String state){
        return (int) addressBook.values().stream().filter(address -> address.getCity().equals(state))
                .count();
    }

    public RepoResponse showContactFromAState(String state){
        Set<Address> collect = addressBook.values().stream().filter(address -> address.getState().equals(state))
                .collect(Collectors.toSet());
        RepoResponse repoResponse = RepoResponse.builder().message("messages from state : " + state)
                .status(200)
                .responseBody(collect).build();
        return repoResponse;
    }

    public RepoResponse showContactFromACity(String city){
        Set<Address> collect = addressBook.values().stream().filter(address -> address.getCity().equals(city))
                .collect(Collectors.toSet());
        RepoResponse repoResponse = RepoResponse.builder().message("messages from city : " + city)
                .status(200)
                .responseBody(collect).build();
        return repoResponse;
    }

    public void printContactBookToConsole(){
        addressBook.values().stream().forEach(consumer);
        //addressBook.values().stream().forEach(System.out::print);
    }

    // Consumer to print address to console
    Consumer<Address> consumer = (address)-> {
        System.out.println(address);
    };

}
