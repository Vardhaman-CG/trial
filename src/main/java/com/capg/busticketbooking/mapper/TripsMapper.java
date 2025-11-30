package com.capg.busticketbooking.mapper;

import com.capg.busticketbooking.dto.TripsDTO;
import com.capg.busticketbooking.entity.Trips;
import com.capg.busticketbooking.entity.Addresses;
import com.capg.busticketbooking.entity.Buses;
import com.capg.busticketbooking.entity.Drivers;
import com.capg.busticketbooking.entity.Routes;

public class TripsMapper {
    public static TripsDTO toDTO(Trips e){
        if (e==null) return null;
        TripsDTO dto = new TripsDTO();
        dto.setTripId(e.getTripId());
        dto.setBoardingAddressId(e.getBoardingAddress()!=null?e.getBoardingAddress().getAddressId():null);
        dto.setDroppingAddressId(e.getDroppingAddress()!=null?e.getDroppingAddress().getAddressId():null);
        dto.setDepartureTime(e.getDepartureTime());
        dto.setArrivalTime(e.getArrivalTime());
        dto.setAvailableSeats(e.getAvailableSeats());
        dto.setFare(e.getFare());
        dto.setTripDate(e.getTripDate());
        dto.setRouteId(e.getRoute()!=null?e.getRoute().getRouteId():null);
        dto.setBusId(e.getBus()!=null?e.getBus().getBusId():null);
        dto.setDriver1Id(e.getDriver1()!=null?e.getDriver1().getDriverId():null);
        dto.setDriver2Id(e.getDriver2()!=null?e.getDriver2().getDriverId():null);
        return dto;
    }
    public static Trips toEntity(TripsDTO dto){
        if (dto==null) return null;
        Trips e = new Trips();
        e.setTripId(dto.getTripId());
        e.setDepartureTime(dto.getDepartureTime());
        e.setArrivalTime(dto.getArrivalTime());
        e.setAvailableSeats(dto.getAvailableSeats());
        e.setFare(dto.getFare());
        e.setTripDate(dto.getTripDate());
        // set relationship references by id (create lightweight proxies)
        if (dto.getBoardingAddressId() != null) {
            Addresses a = new Addresses();
            a.setAddressId(dto.getBoardingAddressId());
            e.setBoardingAddress(a);
        }
        if (dto.getDroppingAddressId() != null) {
            Addresses a2 = new Addresses();
            a2.setAddressId(dto.getDroppingAddressId());
            e.setDroppingAddress(a2);
        }
        if (dto.getBusId() != null) {
            Buses b = new Buses();
            b.setBusId(dto.getBusId());
            e.setBus(b);
        }
        if (dto.getDriver1Id() != null) {
            Drivers d1 = new Drivers();
            d1.setDriverId(dto.getDriver1Id());
            e.setDriver1(d1);
        }
        if (dto.getDriver2Id() != null) {
            Drivers d2 = new Drivers();
            d2.setDriverId(dto.getDriver2Id());
            e.setDriver2(d2);
        }
        if (dto.getRouteId() != null) {
            Routes r = new Routes();
            r.setRouteId(dto.getRouteId());
            e.setRoute(r);
        }
        return e;
    }
}
