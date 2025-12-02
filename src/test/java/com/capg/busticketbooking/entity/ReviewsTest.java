package com.capg.busticketbooking.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class ReviewsTest {

    @Test
    void settersAndDate_shouldWork() {
        Reviews r = new Reviews();
        r.setRating(4);
        r.setComment("Nice trip");
        r.setReviewDate(LocalDateTime.now());

        assertThat(r.getRating()).isEqualTo(4);
        assertThat(r.getComment()).isEqualTo("Nice trip");
        assertThat(r.getReviewDate()).isNotNull();

        assertThat(r.getCustomer()).isNull();
        assertThat(r.getTrip()).isNull();
    }
}

