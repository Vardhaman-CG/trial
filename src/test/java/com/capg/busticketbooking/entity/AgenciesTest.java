package com.capg.busticketbooking.entity;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AgenciesTest {

    @Test
    void gettersAndSetters_shouldWork() {
        Agencies a = new Agencies();
        a.setName("Acme Travels");
        a.setContactPersonName("Alice");
        a.setEmail("alice@acme.com");
        a.setPhone("1234567890");

        assertThat(a.getName()).isEqualTo("Acme Travels");
        assertThat(a.getContactPersonName()).isEqualTo("Alice");
        assertThat(a.getEmail()).isEqualTo("alice@acme.com");
        assertThat(a.getPhone()).isEqualTo("1234567890");

        assertThat(a.getOffices()).isNotNull().isEmpty();
    }
}

