package com.capg.busticketbooking.repository;

import com.capg.busticketbooking.entity.Trips;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TripsRepository extends JpaRepository<Trips, Integer> {
    List<Trips> findByBus_TypeAndTripDate(String type, LocalDateTime tripDate);

}