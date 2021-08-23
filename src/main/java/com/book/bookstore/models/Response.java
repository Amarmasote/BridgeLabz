package com.book.bookstore.models;

import lombok.*;
import org.springframework.http.HttpStatus;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Response {

    private String message;
    private int status;
    private Object body;
}
