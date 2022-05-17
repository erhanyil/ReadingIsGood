package com.example.readingisgood.dto.book;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

@Data
public class BookResponseDTO {
    private Long id;
    private String name;
    private String author;
    private Float price;
    private int stock;
}
