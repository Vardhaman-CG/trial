package com.capg.busticketbooking.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "bookings")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bookings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    private Integer bookingId;

    // Many bookings belong to one trip
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trip_id", nullable = false)
    @NotNull(message = "Trip cannot be null")
    private Trips trip;

    @NotNull(message = "Seat number is required")
    @Min(value = 1, message = "Seat number must be at least 1")
    @Column(name = "seat_number", nullable = false)
    private Integer seatNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    @NotNull(message = "Status is required")
    private Status status = Status.Available;

    public enum Status {
        Available,
        Booked
    }

    // One booking can have multiple payments
    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Payments> payments;


}
