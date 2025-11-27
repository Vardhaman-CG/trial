package com.capg.busticketbooking.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table(name = "agencies")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Agencies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer agency_id;

    private String name;
    private String contact_person_name;
    private String email;
    private String phone;

}
