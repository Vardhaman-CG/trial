package com.capg.busticketbooking.service;

import com.capg.busticketbooking.dto.DriversDTO;

import java.util.List;

public interface DriversService {
    DriversDTO create(DriversDTO dto);
    DriversDTO update(Integer id, DriversDTO dto);
    void delete(Integer id);
    DriversDTO getById(Integer id);
    List<DriversDTO> getAll();
}

