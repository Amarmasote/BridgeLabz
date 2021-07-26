package com.bridgelabz.problems.service;

import java.util.function.Predicate;

public class Validator {

    Predicate<String> validateFirstAndLastName = (name) -> name.length()>=3 &&
                                                         Character.isUpperCase(name.charAt(0));

    Predicate<String> validatePhoneNumber = (number) ->{
        String[] splitNumber = number.split(" ");
        if (number.length() == 11 && Character.isWhitespace(number.charAt(3))
                && splitNumber[1].length() == 10 && splitNumber[0].length() == 2)
            return true;
         else
            return false;
    };

    Predicate<String> validatePassword = (password) -> {

        return password.length() >= 8 && atleastOneUpperCase(password) && atleastOneSpecialCharacter(password);
    };

    boolean atleastOneUpperCase(String string) {
        long upperCase = string.chars().filter(Character::isUpperCase).findFirst().toString().length();
        return upperCase >= 0 ? true : false ;
    };

    boolean atleastOneSpecialCharacter (String string) {
        //negation of regex
        return string.matches("/^[^a-zA-Z0-9]+$/") ? true : false;
    };
}
