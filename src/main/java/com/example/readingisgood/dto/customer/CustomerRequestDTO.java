package com.example.readingisgood.dto.customer;

import lombok.Data;

import java.util.List;

@Data
public class CustomerRequestDTO {

    private String email;
    private String firstName;
    private String lastName;
    private String password;

}
