package com.bridgelabz.problems.model;

import lombok.*;

@Data
@Setter
@Getter
@Builder
@ToString
public class Address {

    private String firstName;
    private String lastName;
    private String area;
    private String city;
    private String state;
    private String zipcode;
    private String phoneNumber;
    private String email;
}
