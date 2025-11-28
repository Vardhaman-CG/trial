package com.capg.busticketbooking.controller;
import com.capg.busticketbooking.entity.Trips;
import com.capg.busticketbooking.service.ITripsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/busticketbooking/trips")
public class TripsController {

    @Autowired
    private ITripsService tripsService;

    @GetMapping("/bus_type/{type}/trip_date/{trip_date}")
    public List<Trips> getTripsByBusTypeAndDate(
            @PathVariable("type") String type,
            @PathVariable("trip_date") String tripDate) {

        // Convert trip_date (YYYY-MM-DD) into LocalDateTime at start of day
        LocalDate date = LocalDate.parse(tripDate);
        LocalDateTime tripDateTime = date.atStartOfDay();

        return tripsService.getTripsByBusTypeAndDate(type, tripDateTime);
    }
}