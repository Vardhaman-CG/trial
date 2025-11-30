package com.capg.busticketbooking.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingsDTO {

    private Integer bookingId;

    // Instead of exposing the whole Trips entity, use tripId (write-only)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Integer tripId;

    private Integer seatNumber;

    private Status status;

    // Payments can be represented as IDs or a simplified DTO (write-only ids)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Integer> paymentIds;

    // Nested objects for responses
    private TripsDTO trip;
    private List<PaymentsDTO> payments;

    public enum Status {
        Available,
        Booked
    }
}
