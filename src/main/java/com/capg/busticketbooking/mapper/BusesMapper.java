package com.capg.busticketbooking.mapper;

import com.capg.busticketbooking.dto.BusesDTO;
import com.capg.busticketbooking.entity.Buses;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class BusesMapper {
    public static BusesDTO toDTO(Buses e){
        if (e==null) return null;
        BusesDTO dto = new BusesDTO();
        dto.setBusId(e.getBusId());
        dto.setRegistrationNumber(e.getRegistrationNumber());
        dto.setCapacity(e.getCapacity());
        dto.setType(e.getType());
        dto.setAgencyOfficeId(e.getAgencyOffice()!=null?e.getAgencyOffice().getOfficeId():null);
        // skip trips list
        return dto;
    }
    public static Buses toEntity(BusesDTO dto){
        if (dto==null) return null;
        Buses e = new Buses();
        e.setBusId(dto.getBusId());
        e.setRegistrationNumber(dto.getRegistrationNumber());
        e.setCapacity(dto.getCapacity());
        e.setType(dto.getType());
        // agencyOffice relation should be set in service if needed
        return e;
    }
    public static List<BusesDTO> toDTOList(List<Buses> list){
        if (list==null) return Collections.emptyList();
        return list.stream().map(BusesMapper::toDTO).collect(Collectors.toList());
    }
}

