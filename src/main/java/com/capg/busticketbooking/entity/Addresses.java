package com.capg.busticketbooking.entity;

import jakarta.persistence.*;
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

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String state;

    @Column(name = "zip_code", nullable = false)
    private String zipCode;

    // One address can belong to many customers
    @OneToMany(mappedBy = "address", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Customer> customers = new ArrayList<>();
///  new  address
    // One address can belong to many agency offices
    @OneToMany(mappedBy = "officeAddress", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Agency_Offices> agencyOffices = new ArrayList<>();

    // One address can belong to many drivers
    @OneToMany(mappedBy = "address_id", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Drivers> drivers = new ArrayList<>();
}