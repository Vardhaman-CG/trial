package com.capg.busticketbooking.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
//paymentsdto
public class PaymentsDTO {

    private Integer paymentId;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Integer bookingId;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Integer customerId;

    private Double amount;

    private LocalDateTime paymentDate;

    private PaymentStatus paymentStatus;

    // Nested objects for responses
    private BookingsDTO booking;
    private CustomerDTO customer;

    public enum PaymentStatus {
        Success, Failed
    }


}