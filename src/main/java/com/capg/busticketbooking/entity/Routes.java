package com.capg.busticketbooking.entity;



import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "routes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Routes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "route_id")
    private Integer routeId;

    @NotBlank(message = "From city is required")
    @Size(max = 255, message = "From city cannot exceed 255 characters")
    @Column(name = "from_city", nullable = false, length = 255)
    private String fromCity;

    @NotBlank(message = "To city is required")
    @Size(max = 255, message = "To city cannot exceed 255 characters")
    @Column(name = "to_city", nullable = false, length = 255)
    private String toCity;

    @Min(value = 0, message = "Break points cannot be negative")
    @Column(name = "break_points")
    private Integer breakPoints;

    @Min(value = 0, message = "Duration cannot be negative")
    @Column(name = "duration")
    private Integer duration;

    // One Route can have many Trips
    @OneToMany(mappedBy = "route", cascade = CascadeType.ALL)
    private List<Trip> trips;
}
