package com.capg.busticketbooking.mapper;

import com.capg.busticketbooking.dto.AddressesDTO;
import com.capg.busticketbooking.entity.Addresses;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class AddressesMapper {

    public static AddressesDTO toDTO(Addresses entity) {
        if (entity == null) return null;
        AddressesDTO dto = AddressesDTO.builder()
                .addressId(entity.getAddressId())
                .address(entity.getAddress())
                .city(entity.getCity())
                .state(entity.getState())
                .zipCode(entity.getZipCode())
                .build();

        // Map related IDs safely
        if (entity.getCustomers() != null) {
            dto.setCustomerIds(entity.getCustomers().stream().map(c -> c.getCustomerId()).collect(Collectors.toList()));
        } else dto.setCustomerIds(Collections.emptyList());

        if (entity.getAgencyOffices() != null) {
            dto.setAgencyOfficeIds(entity.getAgencyOffices().stream().map(a -> a.getOfficeId()).collect(Collectors.toList()));
        } else dto.setAgencyOfficeIds(Collections.emptyList());

        if (entity.getDrivers() != null) {
            dto.setDriverIds(entity.getDrivers().stream().map(d -> d.getDriverId()).collect(Collectors.toList()));
        } else dto.setDriverIds(Collections.emptyList());

        return dto;
    }

    public static Addresses toEntity(AddressesDTO dto) {
        if (dto == null) return null;
        Addresses entity = Addresses.builder()
                .addressId(dto.getAddressId())
                .address(dto.getAddress())
                .city(dto.getCity())
                .state(dto.getState())
                .zipCode(dto.getZipCode())
                .build();
        // related lists are intentionally left untouched; service layer can populate if needed
        return entity;
    }

    public static List<AddressesDTO> toDTOList(List<Addresses> list) {
        if (list == null) return Collections.emptyList();
        return list.stream().map(AddressesMapper::toDTO).collect(Collectors.toList());
    }
}
