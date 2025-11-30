package com.capg.busticketbooking.service.impl;

import com.capg.busticketbooking.dto.TripsDTO;
import com.capg.busticketbooking.entity.Trips;
import com.capg.busticketbooking.mapper.TripsMapper;
import com.capg.busticketbooking.repository.TripsRepository;
import com.capg.busticketbooking.repository.AddressesRepository;
import com.capg.busticketbooking.repository.BusesRepository;
import com.capg.busticketbooking.repository.DriversRepository;
import com.capg.busticketbooking.repository.RoutesRepository;
import com.capg.busticketbooking.service.TripsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TripsServiceImpl2 implements TripsService {

    @Autowired
    private TripsRepository tripsRepository;

    @Autowired
    private AddressesRepository addressesRepository;

    @Autowired
    private BusesRepository busesRepository;

    @Autowired
    private DriversRepository driversRepository;

    @Autowired
    private RoutesRepository routesRepository;

    @Override
    public TripsDTO create(TripsDTO dto) {
        Trips e = TripsMapper.toEntity(dto);
        // Ensure we set managed references for relationships to avoid null FK or transient object issues
        if (dto.getBoardingAddressId() != null) {
            addressesRepository.findById(dto.getBoardingAddressId()).ifPresent(e::setBoardingAddress);
        }
        if (dto.getDroppingAddressId() != null) {
            addressesRepository.findById(dto.getDroppingAddressId()).ifPresent(e::setDroppingAddress);
        }
        if (dto.getBusId() != null) {
            busesRepository.findById(dto.getBusId()).ifPresent(e::setBus);
        }
        if (dto.getDriver1Id() != null) {
            driversRepository.findById(dto.getDriver1Id()).ifPresent(e::setDriver1);
        }
        if (dto.getDriver2Id() != null) {
            driversRepository.findById(dto.getDriver2Id()).ifPresent(e::setDriver2);
        }
        if (dto.getRouteId() != null) {
            routesRepository.findById(dto.getRouteId()).ifPresent(e::setRoute);
        }
        // Validate required relationships are present
        StringBuilder missing = new StringBuilder();
        if (e.getBoardingAddress() == null) missing.append("boardingAddressId ");
        if (e.getDroppingAddress() == null) missing.append("droppingAddressId ");
        if (e.getBus() == null) missing.append("busId ");
        if (missing.length() > 0) {
            throw new IllegalArgumentException("Missing or invalid required foreign keys: " + missing.toString().trim());
        }
        // ensure id cleared for new insert
        e.setTripId(null);
        Trips saved = tripsRepository.save(e);
        return TripsMapper.toDTO(saved);
    }

    @Override
    public TripsDTO update(Integer id, TripsDTO dto) {
        Optional<Trips> opt = tripsRepository.findById(id);
        if (!opt.isPresent()) return null;
        Trips existing = opt.get();
        existing.setDepartureTime(dto.getDepartureTime());
        existing.setArrivalTime(dto.getArrivalTime());
        existing.setAvailableSeats(dto.getAvailableSeats());
        existing.setFare(dto.getFare());
        existing.setTripDate(dto.getTripDate());
        // update relationships if ids provided
        if (dto.getBoardingAddressId() != null) {
            addressesRepository.findById(dto.getBoardingAddressId()).ifPresent(existing::setBoardingAddress);
        }
        if (dto.getDroppingAddressId() != null) {
            addressesRepository.findById(dto.getDroppingAddressId()).ifPresent(existing::setDroppingAddress);
        }
        if (dto.getBusId() != null) {
            busesRepository.findById(dto.getBusId()).ifPresent(existing::setBus);
        }
        if (dto.getDriver1Id() != null) {
            driversRepository.findById(dto.getDriver1Id()).ifPresent(existing::setDriver1);
        }
        if (dto.getDriver2Id() != null) {
            driversRepository.findById(dto.getDriver2Id()).ifPresent(existing::setDriver2);
        }
        if (dto.getRouteId() != null) {
            routesRepository.findById(dto.getRouteId()).ifPresent(existing::setRoute);
        }
        Trips saved = tripsRepository.save(existing);
        return TripsMapper.toDTO(saved);
    }

    @Override
    public void delete(Integer id) {
        tripsRepository.deleteById(id);
    }

    @Override
    public TripsDTO getById(Integer id) {
        return tripsRepository.findById(id).map(TripsMapper::toDTO).orElse(null);
    }

    @Override
    public List<TripsDTO> getAll() {
        return tripsRepository.findAll().stream().map(TripsMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<Trips> getTripsByBusTypeAndDate(String type, String tripDate) {
        return tripsRepository.findByBusTypeAndTripDate(type, tripDate);
    }
}
