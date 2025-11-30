package com.capg.busticketbooking.service;

import com.capg.busticketbooking.dto.PaymentsDTO;

import java.util.List;

public interface PaymentsService {
    PaymentsDTO create(PaymentsDTO dto);
    PaymentsDTO update(Integer id, PaymentsDTO dto);
    void delete(Integer id);
    PaymentsDTO getById(Integer id);
    List<PaymentsDTO> getAll();
}

