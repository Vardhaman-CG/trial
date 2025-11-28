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

public class CustomerDTO {



        private Integer customerId;
        private String name;
        private String email;
        private String phone;

        // Reference to Address (only ID to avoid recursion)
        private Integer addressId;

        // Instead of full entities, use IDs or DTOs
        private List<Integer> paymentIds;
        private List<Integer> reviewIds;
    }
