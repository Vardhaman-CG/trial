package com.capg.busticketbooking.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "agency_offices")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Agency_Offices {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer office_id;

    @ManyToOne
    @JoinColumn(name = "agency_id", nullable = false)
    private Agencies agency_id;

    @NotBlank(message = "Office email is required")
    @Email(message = "Invalid office email format")
    @Size(max = 100, message = "Office email must be less than 100 characters")
    private String office_mail;

    @NotBlank(message = "Office contact person name is required")
    @Size(max = 50, message = "Office contact person name must be less than 50 characters")
    private String office_contact_person_name;

    @NotBlank(message = "Office contact number is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Office contact number must be exactly 10 digits")
    private String office_contact_number;

    @ManyToOne
    @JoinColumn(name = "office_address_id", nullable = false)
    private Addresses office_address_id;
}