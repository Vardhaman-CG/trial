package com.capg.busticketbooking.repository;

import com.capg.busticketbooking.entity.Buses;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusesRepository extends JpaRepository<Buses, Integer> {
}