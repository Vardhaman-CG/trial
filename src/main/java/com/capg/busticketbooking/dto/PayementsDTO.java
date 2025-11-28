package com.capg.busticketbooking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDTO {

    private Integer paymentId;

    private Integer bookingId;

    private Integer customerId;

    private Double amount;

    private LocalDateTime paymentDate;

    private String paymentStatus; // or use enum if you want strict typing
}