package com.capg.busticketbooking.repository;

import com.capg.busticketbooking.entity.Agencies;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgenciesRepository extends JpaRepository<Agencies, Integer> {
}