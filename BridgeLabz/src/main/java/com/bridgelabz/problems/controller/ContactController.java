package com.bridgelabz.problems.controller;

import com.bridgelabz.problems.model.Address;
import com.bridgelabz.problems.model.RepoResponse;
import com.bridgelabz.problems.service.AddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class ContactController {

    @Autowired
    AddressService addressService;

    @PostMapping("/insert/contact")
    public RepoResponse addContact(@RequestBody Address address){
        return addressService.addToContactBook(address);
    }

    @PutMapping("/update/contact")
    public RepoResponse updateContact(@RequestBody Address address){
        return addressService.addToContactBook(address);
    }

    @GetMapping("/get/contact/{firstName}/{lastName}")
    public RepoResponse getContact(@PathVariable String firstName, @PathVariable String lastName){
        return addressService.getFromContactBook(firstName, lastName);
    }

    @DeleteMapping("/get/contact/{firstName}/{lastName}")
    public RepoResponse deleteContact(@PathVariable String firstName, @PathVariable String lastName){
        return addressService.removeFromContactBook(firstName, lastName);
    }

    @GetMapping("/get/contact/city/{city}")
    public RepoResponse getContactsFromCity(@PathVariable String city){

         return addressService.showContactFromACity(city);
    }

    @GetMapping("/get/contact/state/{state}")
    public RepoResponse getContactsFromState(@PathVariable String state){

        return addressService.showContactFromAState(state);
    }

    @GetMapping("/get/contact/number/city/{city}")
    public int getNumberOfContactsFromCity(@PathVariable String city){

        return addressService.findNumberOfContactFromACity(city);
    }


    @GetMapping("/get/contact/number/state/{state}")
    public int getNumberOfContactsFromState(@PathVariable String state){

        return addressService.findNumberOfContactFromAState(state);
    }
}
