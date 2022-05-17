package com.example.readingisgood.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.util.List;

@Table
@Entity
@Data
public class BookModel extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotEmpty(message = "Name is mandatory")
    private String name;

    @Column(nullable = false)
    @NotEmpty(message = "Author is mandatory")
    private String author;

    @Positive(message = "Price must be bigger then zero")
    @Column(nullable = false)
    private Float price;

    @Min(value = 0, message = "Stock must be equal or bigger then zero")
    @Column(nullable = false)
    private int stock;

    @ManyToMany
    @JoinColumn(name = "orderItemId")
    @JsonIgnore
    private List<OrderItemModel> orderItem;
}
