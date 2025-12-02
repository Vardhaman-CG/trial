package com.capg.busticketbooking.service;

import com.capg.busticketbooking.dto.ReviewsDTO;

import java.util.List;

public interface ReviewsService {
    ReviewsDTO create(ReviewsDTO dto);
    ReviewsDTO update(Integer id, ReviewsDTO dto);
    void delete(Integer id);
    ReviewsDTO getById(Integer id);
    List<ReviewsDTO> getAll();

    // New finder methods
    List<ReviewsDTO> findByTrip(Integer tripId);
    List<ReviewsDTO> findByOffice(Integer officeId);
    List<ReviewsDTO> findByAgency(Integer agencyId);
    List<ReviewsDTO> findByDriver(Integer driverId);
    List<ReviewsDTO> findByCustomer(Integer customerId);
}
