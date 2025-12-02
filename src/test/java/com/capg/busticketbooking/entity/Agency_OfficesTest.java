package com.capg.busticketbooking.entity;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Agency_OfficesTest {

    @Test
    void gettersAndCollections_initialization() {
        Agency_Offices office = new Agency_Offices();
        office.setOfficeMail("office@example.com");
        office.setOfficeContactPersonName("Bob");
        office.setOfficeContactNumber("0987654321");

        assertThat(office.getOfficeMail()).isEqualTo("office@example.com");
        assertThat(office.getOfficeContactPersonName()).isEqualTo("Bob");
        assertThat(office.getOfficeContactNumber()).isEqualTo("0987654321");

        assertThat(office.getBuses()).isNotNull().isEmpty();
        assertThat(office.getDrivers()).isNotNull().isEmpty();
    }
}

