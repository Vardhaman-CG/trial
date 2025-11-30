package com.capg.busticketbooking.mapper;

import com.capg.busticketbooking.dto.BookingsDTO;
import com.capg.busticketbooking.entity.Bookings;

public class BookingsMapper {
    public static BookingsDTO toDTO(Bookings e){
        if (e==null) return null;
        BookingsDTO dto = new BookingsDTO();
        dto.setBookingId(e.getBookingId());
        dto.setSeatNumber(e.getSeatNumber());
        // map enum from entity to DTO
        if (e.getStatus() != null) {
            try {
                dto.setStatus(BookingsDTO.Status.valueOf(e.getStatus().name()));
            } catch (IllegalArgumentException ex) {
                // fallback: null
                dto.setStatus(null);
            }
        } else {
            dto.setStatus(null);
        }
        // nested objects
        if (e.getTrip() != null) dto.setTrip(TripsMapper.toDTO(e.getTrip()));
        if (e.getPayments() != null) dto.setPayments(e.getPayments().stream().map(PaymentsMapper::toDTO).toList());
        return dto;
    }
    public static Bookings toEntity(BookingsDTO dto){
        if (dto==null) return null;
        Bookings e = new Bookings();
        e.setBookingId(dto.getBookingId());
        e.setSeatNumber(dto.getSeatNumber());
        // map enum from DTO to entity
        if (dto.getStatus() != null) {
            try {
                e.setStatus(Bookings.Status.valueOf(dto.getStatus().name()));
            } catch (IllegalArgumentException ex) {
                // ignore
            }
        }
        return e;
    }
}
