package com.capg.busticketbooking.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TripsDTO {

    private Integer tripId;

    // Address IDs instead of full Address objects - write-only (accepted on create/update, not returned in responses)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Integer boardingAddressId;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Integer droppingAddressId;

    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;

    private Integer availableSeats;
    private BigDecimal fare;
    private LocalDateTime tripDate;

    // Related entity IDs - write-only
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Integer routeId;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Integer busId;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Integer driver1Id;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Integer driver2Id;

    // Nested DTOs for responses
    private com.capg.busticketbooking.dto.AddressesDTO boardingAddress;
    private com.capg.busticketbooking.dto.AddressesDTO droppingAddress;
    private com.capg.busticketbooking.dto.RoutesDTO route;
    private com.capg.busticketbooking.dto.BusesDTO bus;
    private com.capg.busticketbooking.dto.DriversDTO driver1;
    private com.capg.busticketbooking.dto.DriversDTO driver2;
}