package com.capg.busticketbooking.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "agencies")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Agencies {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "agency_id")
    private Integer agencyId;

    @NotBlank(message = "Agency name cannot be blank")
    @Size(max = 255, message = "Agency name must be less than 255 characters")
    @Column(name = "name")

    private String name;

    @NotBlank(message = "Contact person name is required")
    @Size(max = 30, message = "Contact person name must be less than 30 characters")
    @Column(name = "contact_person_name")

    private String contactPersonName;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Size(max = 255, message = "Email must be less than 255 characters")
    @Column(name = "email")

    private String email;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^[0-9]{10,15}$", message = "Phone number must be between 10–15 digits")
    @Column(name = "phone")
    private String phone;

    // One agency can have many offices
    @OneToMany(mappedBy = "agency", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Agency_Offices> offices = new ArrayList<>();
}
