package com.example.readingisgood.controller;

import com.example.readingisgood.constant.SystemMessage;
import com.example.readingisgood.dto.OrderDTO;
import com.example.readingisgood.dto.BaseResponseDTO;
import com.example.readingisgood.dto.order.OrderRequestDTO;
import com.example.readingisgood.service.CustomerService;
import com.example.readingisgood.service.OrderService;
import lombok.AllArgsConstructor;
    import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/order")
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;

    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<OrderDTO> save(@RequestBody OrderRequestDTO orderRequestDTO) {
        OrderDTO response = orderService.save(customerService.credential().getId(), orderRequestDTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> get(@PathVariable Long id) {
        OrderDTO response = orderService.get(customerService.credential().getId(), id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAll(@RequestParam(required = false) Date startDate, @RequestParam(required = false) Date endDate) {
        List<OrderDTO> response = orderService.getAll(customerService.credential().getId(), startDate, endDate);
        return ResponseEntity.ok(response);
    }

}
