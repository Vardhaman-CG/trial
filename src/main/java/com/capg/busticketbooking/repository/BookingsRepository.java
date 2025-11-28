package com.capg.busticketbooking.repository;

import com.capg.busticketbooking.entity.Bookings;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingsRepository extends JpaRepository<Bookings, Integer> {
}