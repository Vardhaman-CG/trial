package com.capg.busticketbooking.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="drivers")

public class Drivers {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	@Column(name="driver_id",nullable=false)
	private Integer driver_id;
	
	@Column(name="license_number",nullable=false,length=20)
	private String license_number;
	
	@Column(name="name",nullable=false,length=255)
	private String name;
	
	
	@Column(name="phone",nullable=false,length=15)
	private String phone;
	
	@ManyToOne
	@JoinColumn(name = "office_id", referencedColumnName = "office_id")
	private Agency_offices office_id;
	
	@ManyToOne
	@JoinColumn(name="address_id")
	private Addresses address_id;

}
