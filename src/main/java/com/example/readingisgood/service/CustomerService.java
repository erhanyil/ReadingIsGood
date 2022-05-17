package com.example.readingisgood.service;

import com.example.readingisgood.dto.customer.CustomerRequestDTO;
import com.example.readingisgood.dto.customer.CustomerResponseDTO;
import com.example.readingisgood.model.CustomerModel;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface CustomerService extends UserDetailsService {

    CustomerModel loadUserByUsername(String username);

    CustomerResponseDTO save(CustomerRequestDTO customerRequestDTO);

    CustomerResponseDTO get(Long id);

    CustomerModel findById(Long id);

    CustomerResponseDTO credential();
}
