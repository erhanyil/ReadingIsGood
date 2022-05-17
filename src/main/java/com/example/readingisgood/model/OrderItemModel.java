package com.example.readingisgood.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Table
@Entity
@Data
public class OrderItemModel extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Float price;

    private int quantity;

    @ManyToOne
    private BookModel book;

    @ManyToOne
    @JoinColumn(name = "orderId", nullable = false)
    @JsonIgnore
    private OrderModel order;
}
