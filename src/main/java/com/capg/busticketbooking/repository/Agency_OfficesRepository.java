package com.capg.busticketbooking.repository;

import com.capg.busticketbooking.entity.Agency_Offices;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Agency_OfficesRepository extends JpaRepository<Agency_Offices, Integer> {
    List<Agency_Offices> findByAgency_AgencyId(Integer agencyId);
}