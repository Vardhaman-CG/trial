package com.capg.busticketbooking.service;


import com.capg.busticketbooking.entity.Trips;

import java.time.LocalDateTime;
import java.util.List;

public interface ITripsService {

    List<Trips> getTripsByBusTypeAndDate(String type, LocalDateTime tripDate);
}
