package com.capg.busticketbooking.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class PaymentsTest {

    @Test
    void settersAndEnum_shouldWork() {
        Payments p = new Payments();
        p.setAmount(100.0);
        p.setPaymentDate(LocalDateTime.now());
        p.setPaymentStatus(Payments.PaymentStatus.Success);

        assertThat(p.getAmount()).isEqualTo(100.0);
        assertThat(p.getPaymentStatus()).isEqualTo(Payments.PaymentStatus.Success);
        assertThat(p.getPaymentDate()).isNotNull();

        // associations default to null
        assertThat(p.getBooking()).isNull();
        assertThat(p.getCustomer()).isNull();
    }
}

