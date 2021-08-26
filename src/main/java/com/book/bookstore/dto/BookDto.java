package com.book.bookstore.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.*;
import java.io.Serializable;

@Data
@Setter
@Getter
@Builder
@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@ApiModel
public class BookDto implements Serializable {

    private static final long serialVersionUID = 1L;

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
