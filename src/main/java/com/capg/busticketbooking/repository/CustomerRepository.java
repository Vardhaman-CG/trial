package com.capg.busticketbooking.repository;

import com.capg.busticketbooking.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    // search helpers used by /api/customers endpoints
    List<Customer> findByEmailContainingIgnoreCase(String email);
    List<Customer> findByPhoneContaining(String phone);
    List<Customer> findByAddress_CityIgnoreCase(String city);
    List<Customer> findByAddress_StateIgnoreCase(String state);
}