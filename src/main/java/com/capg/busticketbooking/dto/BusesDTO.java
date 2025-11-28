package com.capg.busticketbooking.dto;

import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BusesDTO {

    private Integer busId;

    private String registrationNumber;

    private Integer capacity;

    private String type;

    // Instead of exposing the full Agency_Offices entity, just use its ID
    private Integer agencyOfficeId;

    // Instead of exposing Trips entity directly, you can use trip IDs or a TripsDTO
    private List<Integer> tripIds;
}