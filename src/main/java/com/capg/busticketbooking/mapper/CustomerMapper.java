package com.capg.busticketbooking.mapper;

import com.capg.busticketbooking.dto.CustomerDTO;
import com.capg.busticketbooking.entity.Customer;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CustomerMapper {
    public static CustomerDTO toDTO(Customer e){
        if (e==null) return null;
        CustomerDTO dto = new CustomerDTO();
        dto.setCustomerId(e.getCustomerId());
        dto.setName(e.getName());
        dto.setEmail(e.getEmail());
        dto.setPhone(e.getPhone());
        dto.setAddressId(e.getAddress()!=null?e.getAddress().getAddressId():null);
        return dto;
    }
    public static Customer toEntity(CustomerDTO dto){
        if (dto==null) return null;
        Customer e = new Customer();
        e.setCustomerId(dto.getCustomerId());
        e.setName(dto.getName());
        e.setEmail(dto.getEmail());
        e.setPhone(dto.getPhone());
        return e;
    }
    public static List<CustomerDTO> toDTOList(List<Customer> list){
        if (list==null) return Collections.emptyList();
        return list.stream().map(CustomerMapper::toDTO).collect(Collectors.toList());
    }
}

