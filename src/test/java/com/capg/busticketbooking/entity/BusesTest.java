package com.capg.busticketbooking.entity;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class BusesTest {

    @Test
    void settersAndToString_andCollections() {
        Buses b = new Buses();
        b.setRegistrationNumber("REG123");
        b.setCapacity(40);
        b.setType("AC");

        assertThat(b.getRegistrationNumber()).isEqualTo("REG123");
        assertThat(b.getCapacity()).isEqualTo(40);
        assertThat(b.getType()).isEqualTo("AC");

        // trips collection may be null until set by JPA but check it's allowed
        assertThat(b.getTrips()).isNull();
    }
}

