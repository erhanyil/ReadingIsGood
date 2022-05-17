package com.example.readingisgood.service;

import com.example.readingisgood.dto.OrderDTO;
import com.example.readingisgood.dto.order.OrderRequestDTO;

import java.util.Date;
import java.util.List;

public interface OrderService {

    OrderDTO save(Long customerId, OrderRequestDTO orderRequestDTO);

    OrderDTO get(Long customerId, Long id);

    void delete(Long customerId, Long id);

    List<OrderDTO> getAll(Long customerId, Date startDate, Date endDate);

}
