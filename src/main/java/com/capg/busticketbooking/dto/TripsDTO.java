package com.capg.busticketbooking.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TripsDTO {

    private Integer tripId;

    // Address IDs instead of full Address objects
    private Integer boardingAddressId;
    private Integer droppingAddressId;

    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;

    private Integer availableSeats;
    private BigDecimal fare;
    private LocalDateTime tripDate;

    // Related entity IDs
    private Integer routeId;
    private Integer busId;
    private Integer driver1Id;
    private Integer driver2Id;
}