package com.capg.busticketbooking.service;
import com.capg.busticketbooking.entity.Trips;
import com.capg.busticketbooking.repository.TripsRepository;
import com.capg.busticketbooking.service.ITripsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TripsServiceImpl implements ITripsService {
    @Autowired
    private TripsRepository tripsRepository;


    @Override
    public List<Trips> getTripsByBusTypeAndDate(String type, LocalDateTime tripDate) {
        return tripsRepository.findByBus_TypeAndTripDate(type, tripDate);

    }
}
