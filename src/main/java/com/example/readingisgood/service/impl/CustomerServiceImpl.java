package com.example.readingisgood.service.impl;

import com.example.readingisgood.constant.SystemMessage;
import com.example.readingisgood.dto.customer.CustomerRequestDTO;
import com.example.readingisgood.dto.customer.CustomerResponseDTO;
import com.example.readingisgood.exception.FriendlyException;
import com.example.readingisgood.model.CustomerModel;
import com.example.readingisgood.repository.CustomerRepository;
import com.example.readingisgood.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    private final PasswordEncoder passwordEncoder;

    private final ObjectMapper objectMapper;

    @Override
    public CustomerModel loadUserByUsername(String username) throws UsernameNotFoundException {
        return customerRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
    }

    @Override
    @Transactional
    public CustomerResponseDTO save(CustomerRequestDTO customerRequestDTO) {
        CustomerModel customerModel = objectMapper.convertValue(customerRequestDTO, CustomerModel.class);
        customerModel.setPassword(passwordEncoder.encode(customerRequestDTO.getPassword()));
        customerRepository.save(customerModel);
        return objectMapper.convertValue(customerModel, CustomerResponseDTO.class);
    }

    @Override
    public CustomerResponseDTO get(Long id) {
       CustomerModel customerModel = customerRepository.findById(id).orElseThrow(() -> new FriendlyException(SystemMessage.RECORD_NOT_FOUND));
        return objectMapper.convertValue(customerModel, CustomerResponseDTO.class);
    }

    @Override
    public CustomerModel findById(Long id) {
       return customerRepository.findById(id).orElseThrow(() -> new FriendlyException(SystemMessage.RECORD_NOT_FOUND));
    }

    @Override
    public CustomerResponseDTO credential() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails) principal;
        CustomerModel customerModel = customerRepository.findByEmail(userDetails.getUsername()).orElseThrow(() -> new FriendlyException(SystemMessage.USER_NOT_FOUND));
        return objectMapper.convertValue(customerModel, CustomerResponseDTO.class);
    }
}
