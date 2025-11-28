package com.capg.busticketbooking.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoutesDTO {

    private Integer routeId;

    private String fromCity;

    private String toCity;

    private Integer breakPoints;

    private Integer duration;

    // If you want to show trips info, you can add a List<TripsDTO> here
    // private List<TripsDTO> trips;
}