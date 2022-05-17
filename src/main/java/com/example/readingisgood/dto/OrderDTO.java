package com.example.readingisgood.dto;

import com.example.readingisgood.constant.OrderStatus;
import com.example.readingisgood.dto.order.orderItem.OrderItemDTO;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class OrderDTO {

    private Long id;

    private List<OrderItemDTO> orderItems;

    private Float price;

    private String status;

    private Date orderDate;

}
