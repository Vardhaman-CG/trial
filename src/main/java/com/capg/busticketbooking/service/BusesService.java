package com.capg.busticketbooking.service;

import com.capg.busticketbooking.dto.BusesDTO;

import java.util.List;

public interface BusesService {
    BusesDTO create(BusesDTO dto);
    BusesDTO update(Integer id, BusesDTO dto);
    void delete(Integer id);
    BusesDTO getById(Integer id);
    List<BusesDTO> getAll();
}

