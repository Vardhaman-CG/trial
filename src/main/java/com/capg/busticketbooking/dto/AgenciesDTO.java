package com.capg.busticketbooking.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AgenciesDTO {

    private Integer agencyId;
    private String name;
    private String contactPersonName;
    private String email;
    private String phone;
}