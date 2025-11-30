package com.capg.busticketbooking.mapper;

import com.capg.busticketbooking.dto.ReviewsDTO;
import com.capg.busticketbooking.entity.Reviews;

public class ReviewsMapper {
    public static ReviewsDTO toDTO(Reviews e){
        if (e==null) return null;
        ReviewsDTO dto = new ReviewsDTO();
        dto.setReviewId(e.getReviewId());
        dto.setRating(e.getRating());
        dto.setComment(e.getComment());
        dto.setReviewDate(e.getReviewDate());
        if (e.getCustomer() != null) dto.setCustomer(CustomerMapper.toDTO(e.getCustomer()));
        if (e.getTrip() != null) dto.setTrip(TripsMapper.toDTO(e.getTrip()));
        return dto;
    }
    public static Reviews toEntity(ReviewsDTO dto){
        if (dto==null) return null;
        Reviews e = new Reviews();
        e.setReviewId(dto.getReviewId());
        e.setRating(dto.getRating());
        e.setComment(dto.getComment());
        e.setReviewDate(dto.getReviewDate());
        return e;
    }
}
