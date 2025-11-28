package com.capg.busticketbooking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DriversDTO {

    private Integer driverId;
    private String licenseNumber;
    private String name;
    private String phone;

    // Instead of full entity objects, we expose their IDs
    private Integer officeId;
    private Integer addressId;
}