package com.example.readingisgood.dto.order.orderItem;

import com.example.readingisgood.dto.book.BookResponseDTO;
import lombok.Data;

import javax.validation.constraints.Min;

@Data
public class OrderItemRequestDTO {

    private BookResponseDTO book;

    @Min(value = 1, message = "Min quantity value must be 1")
    private int quantity;
}
