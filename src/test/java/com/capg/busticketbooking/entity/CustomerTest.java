package com.capg.busticketbooking.entity;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CustomerTest {

    @Test
    void builderAndCollections_shouldWork() {
        Addresses addr = Addresses.builder()
                .address("1 Road")
                .city("C")
                .state("S")
                .zipCode("00000")
                .build();

        Customer c = Customer.builder()
                .name("John Doe")
                .email("john@example.com")
                .phone("0123456789")
                .address(addr)
                .build();

        assertThat(c.getName()).isEqualTo("John Doe");
        assertThat(c.getEmail()).isEqualTo("john@example.com");
        assertThat(c.getPhone()).isEqualTo("0123456789");
        assertThat(c.getAddress()).isSameAs(addr);

        // payments and reviews may be null or empty depending on generated code
        assertThat(c.getPayments()).isNullOrEmpty();
        assertThat(c.getReviews()).isNullOrEmpty();
    }
}
