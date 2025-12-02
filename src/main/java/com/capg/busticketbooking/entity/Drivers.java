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
	
	@Column(name="driver_id",nullable=false)
	private Integer driverId;

	@Column(name="license_number",nullable=false,length=20)
	private String licenseNumber;

	@Column(name="name",nullable=false,length=255)
	private String name;
	
	
	@Column(name="phone",nullable=false,length=15)
	private String phone;
	
	@ManyToOne
	@JoinColumn(name = "office_id", referencedColumnName = "office_id")
	private Agency_Offices office;

	@ManyToOne
	@JoinColumn(name="address_id")
	private Addresses address;

}
