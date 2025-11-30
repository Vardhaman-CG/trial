package com.capg.busticketbooking.repository;

import com.capg.busticketbooking.entity.Routes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoutesRepository extends JpaRepository<Routes, Integer> {
    List<Routes> findByFromCityIgnoreCase(String fromCity);
    List<Routes> findByToCityIgnoreCase(String toCity);
    List<Routes> findByFromCityIgnoreCaseAndToCityIgnoreCase(String fromCity, String toCity);
}