package com.capg.busticketbooking.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AgencyOfficesDTO {

    private Integer officeId;
    private Integer agencyId;
    private String officeMail;
    private String officeContactPersonName;
    private String officeContactNumber;
    private Integer officeAddressId;
}