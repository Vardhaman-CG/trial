package com.capg.busticketbooking.service.impl;

import com.capg.busticketbooking.dto.CustomerDTO;
import com.capg.busticketbooking.entity.Customer;
import com.capg.busticketbooking.mapper.CustomerMapper;
import com.capg.busticketbooking.repository.CustomerRepository;
import com.capg.busticketbooking.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public CustomerDTO create(CustomerDTO dto) {
        Customer e = CustomerMapper.toEntity(dto);
        Customer saved = customerRepository.save(e);
        return CustomerMapper.toDTO(saved);
    }

    @Override
    public CustomerDTO update(Integer id, CustomerDTO dto) {
        Optional<Customer> opt = customerRepository.findById(id);
        if (!opt.isPresent()) return null;
        Customer existing = opt.get();
        existing.setName(dto.getName());
        existing.setEmail(dto.getEmail());
        existing.setPhone(dto.getPhone());
        Customer saved = customerRepository.save(existing);
        return CustomerMapper.toDTO(saved);
    }

    @Override
    public void delete(Integer id) {
        customerRepository.deleteById(id);
    }

    @Override
    public CustomerDTO getById(Integer id) {
        return customerRepository.findById(id).map(CustomerMapper::toDTO).orElse(null);
    }

    @Override
    public List<CustomerDTO> getAll() {
        return customerRepository.findAll().stream().map(CustomerMapper::toDTO).collect(Collectors.toList());
    }
}

