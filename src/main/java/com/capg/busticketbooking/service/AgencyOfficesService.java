package com.capg.busticketbooking.service;

import com.capg.busticketbooking.dto.AgencyOfficesDTO;

import java.util.List;

public interface AgencyOfficesService {
    AgencyOfficesDTO create(AgencyOfficesDTO dto);
    AgencyOfficesDTO update(Integer id, AgencyOfficesDTO dto);
    void delete(Integer id);
    AgencyOfficesDTO getById(Integer id);
    List<AgencyOfficesDTO> getAll();
}

