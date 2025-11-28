package com.capg.busticketbooking.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "trips")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Trips {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trip_id")
    private Integer tripId;

    @Column(name = "boarding_address_id", nullable = false)
    private Integer boardingAddressId;

    @Column(name = "dropping_address_id", nullable = false)
    private Integer droppingAddressId;

    @Column(name = "departure_time", nullable = false)
    private LocalDateTime departureTime;

    @Column(name = "arrival_time", nullable = false)
    private LocalDateTime arrivalTime;

    @Column(name = "available_seats", nullable = false)
    private Integer availableSeats;

    @Column(name = "fare", nullable = false, precision = 10, scale = 2)
    private BigDecimal fare;

    @JsonIgnore
    @Column(name = "trip_date", nullable = false)
    private LocalDateTime tripDate;

    // Many trips belong to one route
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "route_id", nullable = false)
    private Routes route;

    // Many trips belong to one bus
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "bus_id", nullable = false)
    private Buses bus;

    // Driver 1 assigned to the trip
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "driver1_driver_id", nullable = false)
    private Drivers driver1;

    // Driver 2 assigned to the trip
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "driver2_driver_id", nullable = false)
    private Drivers driver2;
}