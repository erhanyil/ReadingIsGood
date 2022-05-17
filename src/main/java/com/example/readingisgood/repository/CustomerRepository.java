package com.example.readingisgood.repository;

import com.example.readingisgood.model.CustomerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerModel, Long> {


    Optional<CustomerModel> findByEmail(String email);

}
