package com.capg.busticketbooking.entity;

import jakarta.persistence.*;
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
    @JoinColumn(name = "agency_id")
    private Agencies agency_id;

    private String office_mail;
    private String office_contact_person_name;
    private String office_contact_number;

    @ManyToOne
    @JoinColumn(name = "office_address_id")
    private Addresses office_address_id;
}
