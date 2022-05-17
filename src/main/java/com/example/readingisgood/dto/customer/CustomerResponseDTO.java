package com.example.readingisgood.dto.customer;

import com.example.readingisgood.dto.OrderDTO;
import com.example.readingisgood.model.OrderModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

@Data
public class CustomerResponseDTO {

    private Long id;
    private String email;
    private String firstName;
    private String lastName;
}
