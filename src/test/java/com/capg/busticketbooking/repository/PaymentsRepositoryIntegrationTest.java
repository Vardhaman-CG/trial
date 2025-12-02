package com.capg.busticketbooking.repository;

import com.capg.busticketbooking.entity.Payments;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
public class PaymentsRepositoryIntegrationTest {

    @Autowired
    private PaymentsRepository paymentsRepository;

    @Test
    void debugPaymentsQueries() {
        List<Payments> all = paymentsRepository.findAll();
        System.out.println("ALL payments count=" + all.size());
        for (Payments p : all) {
            System.out.println("paymentId=" + p.getPaymentId() + ", bookingId=" + (p.getBooking() != null ? p.getBooking().getBookingId() : null) + ", customerId=" + (p.getCustomer() != null ? p.getCustomer().getCustomerId() : null));
        }

        // Try native query (officeId 1, 2 as examples)
        for (int officeId : new int[]{1, 2, 3}) {
            List<Payments> byOffice = paymentsRepository.findByOfficeId(officeId);
            System.out.println("findByOfficeId(" + officeId + ") count=" + (byOffice != null ? byOffice.size() : "null"));
        }

        // Try derived navigation method
        for (int officeId : new int[]{1, 2, 3}) {
            List<Payments> byOfficeDerived = paymentsRepository.findByBooking_Trip_Bus_AgencyOffice_OfficeId(officeId);
            System.out.println("findByBooking_Trip_Bus_AgencyOffice_OfficeId(" + officeId + ") count=" + (byOfficeDerived != null ? byOfficeDerived.size() : "null"));
        }

        // Try agency native
        List<Payments> byAgency = paymentsRepository.findByAgencyId(1);
        System.out.println("findByAgencyId(1) count=" + (byAgency != null ? byAgency.size() : "null"));
    }
}

