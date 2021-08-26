package com.book.bookstore.models;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.Length;


import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @ApiModelProperty(example = "book001", required = true, value = "This is book id, it should ne unique")
    @NotEmpty(message = "provide valid book id")
    @Id
    private String bookId;

    @NotEmpty
    @NotNull
    @Length(min = 5)
    @ApiModelProperty(example = "Micro services", required = true, value = "This is book name")
    private String name;


    @NotNull(message = "Author can not be null or empty")
    @ApiModelProperty(example = "Venkat reddy", required = true, value = "Author name")
    private String author;

    @Min(value = 0, message = "value can not be less than 0")
    @Positive(message = "price should be positive value")
    @ApiModelProperty(example = "100", required = true, value = "book price value - MRP")
    private int price;

    @NotNull
    private String shortDesc;
}
