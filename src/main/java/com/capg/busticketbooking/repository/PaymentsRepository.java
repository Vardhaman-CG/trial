package com.capg.busticketbooking.repository;

import com.capg.busticketbooking.entity.Payments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PaymentsRepository extends JpaRepository<Payments, Integer> {

    @Query(value = "SELECT p.* FROM payments p JOIN bookings b ON p.booking_id = b.booking_id JOIN trips t ON b.trip_id = t.trip_id JOIN buses bus ON t.bus_id = bus.bus_id WHERE bus.office_id = :officeId", nativeQuery = true)
    List<Payments> findByOfficeId(@Param("officeId") Integer officeId);

    @Query(value = "SELECT p.* FROM payments p JOIN bookings b ON p.booking_id = b.booking_id JOIN trips t ON b.trip_id = t.trip_id JOIN buses bus ON t.bus_id = bus.bus_id JOIN agency_offices ao ON bus.office_id = ao.office_id WHERE ao.agency_id = :agencyId", nativeQuery = true)
    List<Payments> findByAgencyId(@Param("agencyId") Integer agencyId);

    List<Payments> findByCustomer_CustomerId(Integer customerId);

    // Allow service layer to remove payments tied to a booking before deleting the booking
    void deleteByBooking_BookingId(Integer bookingId);

    // Optional: fetch for checks
    List<Payments> findByBooking_BookingId(Integer bookingId);
}