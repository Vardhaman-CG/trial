package com.capg.busticketbooking.entity;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class DriversTest {

    @Test
    void gettersAndAssociations_shouldWork() {
        Drivers d = new Drivers();
        d.setLicenseNumber("LIC123");
        d.setName("Driver A");
        d.setPhone("9876543210");

        assertThat(d.getLicenseNumber()).isEqualTo("LIC123");
        assertThat(d.getName()).isEqualTo("Driver A");
        assertThat(d.getPhone()).isEqualTo("9876543210");

        // associations default to null
        assertThat(d.getOffice()).isNull();
        assertThat(d.getAddress()).isNull();
    }
}

