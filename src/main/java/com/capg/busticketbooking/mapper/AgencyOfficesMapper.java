package com.capg.busticketbooking.mapper;

import com.capg.busticketbooking.dto.AgencyOfficesDTO;
import com.capg.busticketbooking.entity.Agency_Offices;

public class AgencyOfficesMapper {
    public static AgencyOfficesDTO toDTO(Agency_Offices e){
        if (e==null) return null;
        AgencyOfficesDTO dto = new AgencyOfficesDTO();
        dto.setOfficeId(e.getOfficeId());
        dto.setAgencyId(e.getAgency()!=null?e.getAgency().getAgencyId():null);
        dto.setOfficeMail(e.getOfficeMail());
        dto.setOfficeContactPersonName(e.getOfficeContactPersonName());
        dto.setOfficeContactNumber(e.getOfficeContactNumber());
        dto.setOfficeAddressId(e.getOfficeAddress()!=null?e.getOfficeAddress().getAddressId():null);
        return dto;
    }
    public static Agency_Offices toEntity(AgencyOfficesDTO dto){
        if (dto==null) return null;
        Agency_Offices e = new Agency_Offices();
        e.setOfficeId(dto.getOfficeId());
        e.setOfficeMail(dto.getOfficeMail());
        e.setOfficeContactPersonName(dto.getOfficeContactPersonName());
        e.setOfficeContactNumber(dto.getOfficeContactNumber());
        return e;
    }
}

