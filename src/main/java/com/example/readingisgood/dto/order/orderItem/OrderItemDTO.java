package com.example.readingisgood.dto.order.orderItem;

import com.example.readingisgood.dto.book.BookResponseDTO;
import lombok.Data;

@Data
public class OrderItemDTO {

    private BookResponseDTO book;
    private int quantity;
    private Float price;

}
