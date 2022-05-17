package com.example.readingisgood.dto.order;

import com.example.readingisgood.dto.order.orderItem.OrderItemRequestDTO;
import lombok.Data;

import java.util.List;

@Data
public class OrderRequestDTO {

    private List<OrderItemRequestDTO> orderItems;

}
