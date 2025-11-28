package com.capg.busticketbooking.repository;

import com.capg.busticketbooking.entity.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewsRepository extends JpaRepository<Reviews, Integer> {
}