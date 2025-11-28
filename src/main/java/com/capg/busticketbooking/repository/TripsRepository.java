package com.capg.busticketbooking.repository;

import com.capg.busticketbooking.entity.Trips;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripsRepository extends JpaRepository<Trips, Integer> {
}