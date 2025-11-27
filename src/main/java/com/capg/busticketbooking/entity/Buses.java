package com.capg.busticketbooking.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "buses")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Buses {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bus_id")
    private Integer busId;

    @NotNull(message = "Office ID cannot be null")
    @Column(name = "office_id", nullable = false)
    private Integer officeId;

    @NotBlank(message = "Registration number is required")
    @Size(max = 20, message = "Registration number cannot exceed 20 characters")
    @Column(name = "registration_number", nullable = false, length = 20)
    private String registrationNumber;

    @NotNull(message = "Capacity is required")
    @Min(value = 1, message = "Capacity must be at least 1")
    @Column(name = "capacity", nullable = false)
    private Integer capacity;

    @NotBlank(message = "Type is required")
    @Size(max = 30, message = "Type cannot exceed 30 characters")
    @Column(name = "type", nullable = false, length = 30)
    private String type;

    // One Bus can have many Trips
    @OneToMany(mappedBy = "bus", cascade = CascadeType.ALL)
    private List<Trip> trips;
}
