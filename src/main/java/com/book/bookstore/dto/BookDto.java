package com.book.bookstore.dto;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Setter
@Getter
@Builder
@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {

    @NotEmpty
    @Id
    private String bookId;
    @NotEmpty
    @NotNull
    @Length(min = 5)
    private String name;
    private String author;
    @Min(0)
    private int price;
    private String shortDesc;
}
