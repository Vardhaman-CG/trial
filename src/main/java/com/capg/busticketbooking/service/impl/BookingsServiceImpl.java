package com.capg.busticketbooking.service.impl;

import com.capg.busticketbooking.dto.BookingsDTO;
import com.capg.busticketbooking.entity.Bookings;
import com.capg.busticketbooking.entity.Trips;
import com.capg.busticketbooking.mapper.BookingsMapper;
import com.capg.busticketbooking.repository.BookingsRepository;
import com.capg.busticketbooking.repository.PaymentsRepository;
import com.capg.busticketbooking.repository.TripsRepository;
import com.capg.busticketbooking.service.BookingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookingsServiceImpl implements BookingsService {

    @Autowired
    private BookingsRepository bookingsRepository;

    @Autowired
    private PaymentsRepository paymentsRepository;

    @Autowired
    private TripsRepository tripsRepository;

    @Override
    public BookingsDTO create(BookingsDTO dto) {
        if (dto == null || dto.getTripId() == null) return null;
        Optional<Trips> tripOpt = tripsRepository.findById(dto.getTripId());
        if (!tripOpt.isPresent()) return null;
        Trips trip = tripOpt.get();

        Bookings e = BookingsMapper.toEntity(dto);
        // ensure new insert
        e.setBookingId(null);
        // set managed Trip entity
        e.setTrip(trip);
        Bookings saved = bookingsRepository.save(e);
        return BookingsMapper.toDTO(saved);
    }

    @Override
    public BookingsDTO update(Integer id, BookingsDTO dto) {
        Optional<Bookings> opt = bookingsRepository.findById(id);
        if (!opt.isPresent()) return null;
        Bookings existing = opt.get();
        existing.setSeatNumber(dto.getSeatNumber());
        Bookings saved = bookingsRepository.save(existing);
        return BookingsMapper.toDTO(saved);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        // remove dependent payments first to satisfy DB FK constraints
        paymentsRepository.deleteByBooking_BookingId(id);
        bookingsRepository.deleteById(id);
    }

    @Override
    public BookingsDTO getById(Integer id) {
        return bookingsRepository.findById(id).map(BookingsMapper::toDTO).orElse(null);
    }

    @Override
    public List<BookingsDTO> getAll() {
        return bookingsRepository.findAll().stream().map(BookingsMapper::toDTO).collect(Collectors.toList());
    }
}
