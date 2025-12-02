package com.capg.busticketbooking.service.impl;

import com.capg.busticketbooking.dto.CustomerDTO;
import com.capg.busticketbooking.entity.Customer;
import com.capg.busticketbooking.entity.Addresses;
import com.capg.busticketbooking.mapper.CustomerMapper;
import com.capg.busticketbooking.repository.CustomerRepository;
import com.capg.busticketbooking.repository.AddressesRepository;
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

    @Autowired
    private AddressesRepository addressesRepository;

    @Override
    public CustomerDTO create(CustomerDTO dto) {
        // Require addressId to be provided so non-nullable FK can be set
        if (dto == null || dto.getAddressId() == null) {
            return null;
        }

        Customer e = CustomerMapper.toEntity(dto);
        // set address association
        Optional<Addresses> addrOpt = addressesRepository.findById(dto.getAddressId());
        if (!addrOpt.isPresent()) {
            // address id provided but not found -> indicate failure to caller (null handled by controller)
            return null;
        }
        e.setAddress(addrOpt.get());

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
        // update address association if provided (allow setting to null)
        if (dto.getAddressId() != null) {
            Optional<Addresses> addrOpt = addressesRepository.findById(dto.getAddressId());
            if (!addrOpt.isPresent()) {
                // cannot update to a non-existent address
                return null;
            }
            existing.setAddress(addrOpt.get());
        }
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
