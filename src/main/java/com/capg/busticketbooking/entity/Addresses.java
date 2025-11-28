package com.capg.busticketbooking.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "addresses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Addresses {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Integer addressId;

    @NotBlank(message = "Address cannot be blank")
    @Size(max = 255, message = "Address must not exceed 255 characters")
    @Column(nullable = false)
    private String address;

    @NotBlank(message = "City cannot be blank")
    @Size(max = 100, message = "City must not exceed 100 characters")
    @Column(nullable = false)
    private String city;

    @NotBlank(message = "State cannot be blank")
    @Size(max = 100, message = "State must not exceed 100 characters")
    @Column(nullable = false)
    private String state;

    @NotBlank(message = "Zip code cannot be blank")
    @Size(max = 20, message = "Zip code must not exceed 20 characters")
    @Column(name = "zip_code", nullable = false)
    private String zipCode;

    // One address can belong to many customers
    @OneToMany(mappedBy = "address", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Customer> customers = new ArrayList<>();

    // One address can belong to many agency offices
    @OneToMany(mappedBy = "office_address_id", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Agency_Offices> agencyOffices = new ArrayList<>();

    // One address can belong to many drivers
    @OneToMany(mappedBy = "address", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Drivers> drivers = new ArrayList<>();
}