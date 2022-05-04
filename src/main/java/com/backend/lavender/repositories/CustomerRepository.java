package com.backend.lavender.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import com.backend.lavender.models.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByEmail(String email);
    List<Customer> findByPhoneNumber(String phoneNumber);
}
