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
    @Column(name = "office_id")
    private Integer officeId;

    @ManyToOne
    @JoinColumn(name = "agency_id", nullable = false)
    private Agencies agency;

    @NotBlank
    @Email
    @Size(max = 100)
    @Column(name = "office_mail")
    private String officeMail;

    @NotBlank
    @Size(max = 50)
    @Column(name = "office_contact_person_name")
    private String officeContactPersonName;

    @NotBlank
    @Pattern(regexp = "^[0-9]{10}$")
    @Column(name = "office_contact_number")
    private String officeContactNumber;

    @ManyToOne
    @JoinColumn(name = "office_address_id", nullable = false)
    private Addresses officeAddress;
}