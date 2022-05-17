package com.example.readingisgood.model;

import com.example.readingisgood.constant.OrderStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Table
@Entity
@Data
public class OrderModel extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Float price;

    private String status = OrderStatus.WAITING.name();

    private Date orderDate = new Date();

    @ManyToOne
    @JoinColumn(name = "customerId", nullable = false)
    @JsonIgnore
    private CustomerModel customer;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    private List<OrderItemModel> orderItem;
}
