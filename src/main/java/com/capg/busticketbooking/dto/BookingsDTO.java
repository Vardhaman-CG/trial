package com.capg.busticketbooking.dto;

import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingsDTO {

    private Integer bookingId;

    // Instead of exposing the whole Trips entity, use tripId
    private Integer tripId;

    private Integer seatNumber;

    private Status status;

    // Payments can be represented as IDs or a simplified DTO
    private List<Integer> paymentIds;

    public enum Status {
        Available,
        Booked
    }
}
