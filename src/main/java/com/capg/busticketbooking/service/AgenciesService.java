package com.capg.busticketbooking.service;

import com.capg.busticketbooking.dto.AgenciesDTO;

import java.util.List;

public interface AgenciesService {
    AgenciesDTO create(AgenciesDTO dto);
    AgenciesDTO update(Integer id, AgenciesDTO dto);
    void delete(Integer id);
    AgenciesDTO getById(Integer id);
    List<AgenciesDTO> getAll();
}

