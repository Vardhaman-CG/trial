package com.capg.busticketbooking.repository;

import com.capg.busticketbooking.entity.Payments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentsRepository extends JpaRepository<Payments, Integer> {
}