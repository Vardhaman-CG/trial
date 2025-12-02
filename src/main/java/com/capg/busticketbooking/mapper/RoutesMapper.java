package com.capg.busticketbooking.mapper;

import com.capg.busticketbooking.dto.RoutesDTO;
import com.capg.busticketbooking.entity.Routes;

public class RoutesMapper {
    public static RoutesDTO toDTO(Routes e){
        if (e==null) return null;
        RoutesDTO dto = new RoutesDTO();
        dto.setRouteId(e.getRouteId());
        dto.setFromCity(e.getFromCity());
        dto.setToCity(e.getToCity());
        dto.setBreakPoints(e.getBreakPoints());
        dto.setDuration(e.getDuration());
        return dto;
    }
    public static Routes toEntity(RoutesDTO dto){
        if (dto==null) return null;
        Routes e = new Routes();
        e.setRouteId(dto.getRouteId());
        e.setFromCity(dto.getFromCity());
        e.setToCity(dto.getToCity());
        e.setBreakPoints(dto.getBreakPoints());
        e.setDuration(dto.getDuration());
        return e;
    }
}

