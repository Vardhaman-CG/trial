package com.capg.busticketbooking.entity;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class TripsTest {

    @Test
    void settersAndCollections_shouldWork() {
        Trips t = new Trips();
        t.setAvailableSeats(30);
        t.setFare(new BigDecimal("250.50"));
        t.setDepartureTime(LocalDateTime.now());
        t.setArrivalTime(LocalDateTime.now().plusHours(5));

        assertThat(t.getAvailableSeats()).isEqualTo(30);
        assertThat(t.getFare()).isEqualByComparingTo(new BigDecimal("250.50"));
        assertThat(t.getDepartureTime()).isNotNull();
        assertThat(t.getArrivalTime()).isNotNull();

        assertThat(t.getBookings()).isNotNull().isEmpty();
        assertThat(t.getReviews()).isNotNull().isEmpty();
    }
}

