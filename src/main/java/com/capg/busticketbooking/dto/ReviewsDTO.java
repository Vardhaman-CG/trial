package com.capg.busticketbooking.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
//reviewsdto
public class ReviewsDTO {

    private Integer reviewId;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Integer customerId;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Integer tripId;

    private Integer rating;

    private String comment;

    private LocalDateTime reviewDate;

    // nested objects for responses
    private CustomerDTO customer;
    private TripsDTO trip;
}