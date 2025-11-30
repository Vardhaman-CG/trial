package com.capg.busticketbooking.service;

import com.capg.busticketbooking.dto.AddressesDTO;

import java.util.List;

public interface AddressesService {
    AddressesDTO create(AddressesDTO dto);
    AddressesDTO update(Integer id, AddressesDTO dto);
    void delete(Integer id);
    AddressesDTO getById(Integer id);
    List<AddressesDTO> getAll();
}

