package com.capg.busticketbooking.repository;

import com.capg.busticketbooking.entity.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewsRepository extends JpaRepository<Reviews, Integer> {
    List<Reviews> findByTrip_TripId(Integer tripId);
    List<Reviews> findByCustomer_CustomerId(Integer customerId);

    // Reviews for an office: join reviews -> trips -> buses -> agency_offices
    @Query(value = "SELECT r.* FROM reviews r JOIN trips t ON r.trip_id = t.trip_id JOIN buses b ON t.bus_id = b.bus_id WHERE b.office_id = :officeId", nativeQuery = true)
    List<Reviews> findByOfficeId(@Param("officeId") Integer officeId);

    // Reviews for an agency: join buses -> agency_offices -> agency
    @Query(value = "SELECT r.* FROM reviews r JOIN trips t ON r.trip_id = t.trip_id JOIN buses b ON t.bus_id = b.bus_id JOIN agency_offices ao ON b.office_id = ao.office_id WHERE ao.agency_id = :agencyId", nativeQuery = true)
    List<Reviews> findByAgencyId(@Param("agencyId") Integer agencyId);

    // Reviews for a driver (either driver1 or driver2)
    @Query(value = "SELECT r.* FROM reviews r JOIN trips t ON r.trip_id = t.trip_id WHERE t.driver1_driver_id = :driverId OR t.driver2_driver_id = :driverId", nativeQuery = true)
    List<Reviews> findByDriverId(@Param("driverId") Integer driverId);

    // Reviews for an office id (duplicate of findByOfficeId) kept for semantic clarity
}