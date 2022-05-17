package com.example.readingisgood.dto.book;

import lombok.Data;

import javax.validation.constraints.Min;

@Data
public class BookRequestDTO {
    private String name;
    private String author;
    private Float price;

    @Min(value = 0, message = "stock must be minimum 0")
    private int stock;
}
