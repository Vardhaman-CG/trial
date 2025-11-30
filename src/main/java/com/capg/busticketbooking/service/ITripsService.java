package com.capg.busticketbooking.service;


import com.capg.busticketbooking.entity.Trips;

import java.util.List;

public interface ITripsService {

    List<Trips> getTripsByBusTypeAndDate(String type, String tripDate);
}
