package com.capg.busticketbooking.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "drivers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Drivers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "driver_id", nullable = false)
    private Integer driverId;

    @Column(name = "license_number", nullable = false, length = 20)
    private String licenseNumber;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "phone", nullable = false, length = 15)
    private String phone;

    // Many drivers belong to one agency office
    @ManyToOne
    @JoinColumn(name = "office_id", referencedColumnName = "office_id", nullable = false)
    private Agency_Offices office;

    // Many drivers belong to one address
    @ManyToOne
    @JoinColumn(name = "address_id", referencedColumnName = "address_id", nullable = false)
    private Addresses address;
}