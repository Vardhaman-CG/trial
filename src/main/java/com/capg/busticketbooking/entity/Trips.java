package com.capg.busticketbooking.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


//trips1
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

    @ManyToOne
    @JoinColumn(name = "boarding_address_id", nullable = false)
    private Addresses boardingAddress;

    @ManyToOne
    @JoinColumn(name = "dropping_address_id", nullable = false)
    private Addresses droppingAddress;

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

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "route_id")
    private Routes route;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "bus_id", nullable = false)
    private Buses bus;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "driver1_driver_id", nullable = false)
    private Drivers driver1;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "driver2_driver_id", nullable = false)
    private Drivers driver2;

    // Reverse relationships
    @OneToMany(mappedBy = "trip", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Bookings> bookings;

//     @OneToMany(mappedBy = "trip", cascade = CascadeType.ALL, orphanRemoval = true)
//     private List<Reviews> reviews;
}