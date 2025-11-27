
package com.capg.busticketbooking.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "reviews")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reviews {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reviewId;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    @NotNull(message = "Customer must not be null")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "trip_id", nullable = false)
    @NotNull(message = "Trip must not be null")
    private Trip trip;

    @NotNull(message = "Rating must not be null")
    @Min(value = 1, message = "Rating must be at least 1")
    @Max(value = 5, message = "Rating must not exceed 5")
    private Integer rating;

    @NotBlank(message = "Comment must not be blank")
    @Size(max = 1000, message = "Comment must not exceed 1000 characters")
    private String comment;

    @NotNull(message = "Review date must not be null")
    @PastOrPresent(message = "Review date cannot be in the future")
    private LocalDateTime reviewDate;
}