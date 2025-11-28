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

    @NotBlank
    @Size(max = 20)
    @Column(name = "registration_number", nullable = false, length = 20)
    private String registrationNumber;

    @NotNull
    @Min(1)
    @Column(name = "capacity", nullable = false)
    private Integer capacity;

    @NotBlank
    @Size(max = 30)
    @Column(name = "type", nullable = false, length = 30)
    private String type;

    @ManyToOne
    @JoinColumn(name = "office_id", nullable = false)
    private Agency_Offices agencyOffice;

    // One Bus can have many Trips
    @OneToMany(mappedBy = "bus", cascade = CascadeType.ALL)
    private List<Trips> trips;
}