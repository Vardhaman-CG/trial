package com.capg.busticketbooking.service;

import com.capg.busticketbooking.dto.RoutesDTO;

import java.util.List;

public interface RoutesService {
    RoutesDTO create(RoutesDTO dto);
    RoutesDTO update(Integer id, RoutesDTO dto);
    void delete(Integer id);
    RoutesDTO getById(Integer id);
    List<RoutesDTO> getAll();
}

