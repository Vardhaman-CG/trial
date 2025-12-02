package com.capg.busticketbooking.mapper;

import com.capg.busticketbooking.dto.AgenciesDTO;
import com.capg.busticketbooking.entity.Agencies;

public class AgenciesMapper {
    public static AgenciesDTO toDTO(Agencies e){
        if (e==null) return null;
        AgenciesDTO dto = new AgenciesDTO();
        dto.setAgencyId(e.getAgencyId());
        dto.setName(e.getName());
        dto.setContactPersonName(e.getContactPersonName());
        dto.setEmail(e.getEmail());
        dto.setPhone(e.getPhone());
        return dto;
    }
    public static Agencies toEntity(AgenciesDTO dto){
        if (dto==null) return null;
        Agencies e = new Agencies();
        e.setAgencyId(dto.getAgencyId());
        e.setName(dto.getName());
        e.setContactPersonName(dto.getContactPersonName());
        e.setEmail(dto.getEmail());
        e.setPhone(dto.getPhone());
        return e;
    }
}

