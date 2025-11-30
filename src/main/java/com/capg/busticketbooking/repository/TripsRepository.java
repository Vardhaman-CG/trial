package com.capg.busticketbooking.repository;

import com.capg.busticketbooking.entity.Trips;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TripsRepository extends JpaRepository<Trips, Integer> {
    // Use a native query to compare only the date part (MySQL DATE()) and join to buses for type
    @Query(value = "SELECT t.* FROM trips t JOIN buses b ON t.bus_id = b.bus_id WHERE b.type = :type AND DATE(t.trip_date) = :tripDate", nativeQuery = true)
    List<Trips> findByBusTypeAndTripDate(@Param("type") String type, @Param("tripDate") String tripDate);

    // Search by boarding city
    List<Trips> findByBoardingAddress_CityIgnoreCase(String fromCity);

    // Search by dropping city
    List<Trips> findByDroppingAddress_CityIgnoreCase(String toCity);

    // Search by trip date (compare DATE portion)
    @Query(value = "SELECT * FROM trips t WHERE DATE(t.trip_date) = :tripDate", nativeQuery = true)
    List<Trips> findByTripDate(@Param("tripDate") String tripDate);

    // Combined search by from,to and date
    @Query(value = "SELECT t.* FROM trips t JOIN addresses a1 ON t.boarding_address_id = a1.address_id JOIN addresses a2 ON t.dropping_address_id = a2.address_id JOIN buses b ON t.bus_id = b.bus_id WHERE LOWER(a1.city) = LOWER(:fromCity) AND LOWER(a2.city) = LOWER(:toCity) AND DATE(t.trip_date) = :tripDate", nativeQuery = true)
    List<Trips> findByFromToAndDate(@Param("fromCity") String fromCity, @Param("toCity") String toCity, @Param("tripDate") String tripDate);

    // Combined search by from,to,date and bus type
    @Query(value = "SELECT t.* FROM trips t JOIN addresses a1 ON t.boarding_address_id = a1.address_id JOIN addresses a2 ON t.dropping_address_id = a2.address_id JOIN buses b ON t.bus_id = b.bus_id WHERE LOWER(a1.city) = LOWER(:fromCity) AND LOWER(a2.city) = LOWER(:toCity) AND DATE(t.trip_date) = :tripDate AND LOWER(b.type) = LOWER(:busType)", nativeQuery = true)
    List<Trips> findByFromToDateAndBusType(@Param("fromCity") String fromCity, @Param("toCity") String toCity, @Param("tripDate") String tripDate, @Param("busType") String busType);
}