package com.capg.busticketbooking.dto;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressesDTO {
        private Integer addressId;
        private String address;
        private String city;
        private String state;
        private String zipCode;

        // Instead of full entities, use IDs or DTOs
        private List<Integer> customerIds;
        private List<Integer> agencyOfficeIds;
        private List<Integer> driverIds;




    }

