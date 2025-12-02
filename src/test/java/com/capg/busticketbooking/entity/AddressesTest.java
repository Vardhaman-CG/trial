package com.capg.busticketbooking.entity;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AddressesTest {

    @Test
    void builderAndGetters_shouldWork() {
        Addresses addr = Addresses.builder()
                .address("123 Main St")
                .city("Townsville")
                .state("TS")
                .zipCode("12345")
                .build();

        assertThat(addr.getAddress()).isEqualTo("123 Main St");
        assertThat(addr.getCity()).isEqualTo("Townsville");
        assertThat(addr.getState()).isEqualTo("TS");
        assertThat(addr.getZipCode()).isEqualTo("12345");

        // collections may be initialized by the entity or left null depending on how JPA/Lombok was processed
        assertThat(addr.getCustomers()).isNullOrEmpty();
        assertThat(addr.getAgencyOffices()).isNullOrEmpty();
        assertThat(addr.getDrivers()).isNullOrEmpty();
    }
}
