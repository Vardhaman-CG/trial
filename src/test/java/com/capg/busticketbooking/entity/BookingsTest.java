package com.capg.busticketbooking.entity;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BookingsTest {

    @Test
    void enumDefaultAndSetters_shouldWork() {
        Bookings booking = new Bookings();
        booking.setSeatNumber(5);

        // default status should be Available
        assertThat(booking.getStatus()).isEqualTo(Bookings.Status.Available);

        // change status and verify
        booking.setStatus(Bookings.Status.Booked);
        assertThat(booking.getStatus()).isEqualTo(Bookings.Status.Booked);

        booking.setSeatNumber(10);
        assertThat(booking.getSeatNumber()).isEqualTo(10);

        assertThat(booking.getPayments()).isNull();
    }
}

