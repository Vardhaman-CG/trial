package com.capg.busticketbooking.service;

import com.capg.busticketbooking.dto.TripsDTO;

import java.util.List;

public interface TripsService {
    TripsDTO create(TripsDTO dto);
    TripsDTO update(Integer id, TripsDTO dto);
    void delete(Integer id);
    TripsDTO getById(Integer id);
    List<TripsDTO> getAll();

    // existing custom query
    java.util.List<com.capg.busticketbooking.entity.Trips> getTripsByBusTypeAndDate(String type, String tripDate);
}

