package com.capg.busticketbooking.entity;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RoutesTest {

    @Test
    void gettersAndTripsCollection_shouldWork() {
        Routes r = new Routes();
        r.setFromCity("A City");
        r.setToCity("B City");
        r.setBreakPoints(2);
        r.setDuration(120);

        assertThat(r.getFromCity()).isEqualTo("A City");
        assertThat(r.getToCity()).isEqualTo("B City");
        assertThat(r.getBreakPoints()).isEqualTo(2);
        assertThat(r.getDuration()).isEqualTo(120);

        assertThat(r.getTrips()).isNotNull().isEmpty();
    }
}

