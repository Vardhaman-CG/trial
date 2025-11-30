package com.capg.busticketbooking.service;

import com.capg.busticketbooking.dto.BookingsDTO;

import java.util.List;

public interface BookingsService {
    BookingsDTO create(BookingsDTO dto);
    BookingsDTO update(Integer id, BookingsDTO dto);
    void delete(Integer id);
    BookingsDTO getById(Integer id);
    List<BookingsDTO> getAll();
}

