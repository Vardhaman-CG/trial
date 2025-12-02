package com.capg.busticketbooking.mapper;

import com.capg.busticketbooking.dto.DriversDTO;
import com.capg.busticketbooking.entity.Drivers;

public class DriversMapper {
    public static DriversDTO toDTO(Drivers e){
        if (e==null) return null;
        DriversDTO dto = new DriversDTO();
        dto.setDriverId(e.getDriverId());
        dto.setLicenseNumber(e.getLicenseNumber());
        dto.setName(e.getName());
        dto.setPhone(e.getPhone());
        dto.setOfficeId(e.getOffice()!=null?e.getOffice().getOfficeId():null);
        dto.setAddressId(e.getAddress()!=null?e.getAddress().getAddressId():null);
        return dto;
    }
    public static Drivers toEntity(DriversDTO dto){
        if (dto==null) return null;
        Drivers e = new Drivers();
        e.setDriverId(dto.getDriverId());
        e.setLicenseNumber(dto.getLicenseNumber());
        e.setName(dto.getName());
        e.setPhone(dto.getPhone());
        return e;
    }
}
