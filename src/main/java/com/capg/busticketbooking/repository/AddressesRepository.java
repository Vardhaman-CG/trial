package com.capg.busticketbooking.repository;

import com.capg.busticketbooking.entity.Addresses;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressesRepository extends JpaRepository<Addresses, Integer> {
}