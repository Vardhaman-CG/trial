package com.capg.busticketbooking.service;

import com.capg.busticketbooking.dto.CustomerDTO;

import java.util.List;

public interface CustomerService {
    CustomerDTO create(CustomerDTO dto);
    CustomerDTO update(Integer id, CustomerDTO dto);
    void delete(Integer id);
    CustomerDTO getById(Integer id);
    List<CustomerDTO> getAll();
}

