package com.capg.busticketbooking.mapper;

import com.capg.busticketbooking.dto.PaymentsDTO;
import com.capg.busticketbooking.entity.Payments;

public class PaymentsMapper {
    public static PaymentsDTO toDTO(Payments e){
        if (e==null) return null;
        PaymentsDTO dto = new PaymentsDTO();
        dto.setPaymentId(e.getPaymentId());
        dto.setBookingId(e.getBooking()!=null?e.getBooking().getBookingId():null);
        dto.setCustomerId(e.getCustomer()!=null?e.getCustomer().getCustomerId():null);
        dto.setAmount(e.getAmount());
        dto.setPaymentDate(e.getPaymentDate());
        if (e.getPaymentStatus() != null) {
            try {
                dto.setPaymentStatus(PaymentsDTO.PaymentStatus.valueOf(e.getPaymentStatus().name()));
            } catch (IllegalArgumentException ex) {
                dto.setPaymentStatus(null);
            }
        } else dto.setPaymentStatus(null);
        return dto;
    }
    public static Payments toEntity(PaymentsDTO dto){
        if (dto==null) return null;
        Payments e = new Payments();
        e.setPaymentId(dto.getPaymentId());
        e.setAmount(dto.getAmount());
        // map DTO enum to entity enum
        if (dto.getPaymentStatus() != null) {
            try {
                e.setPaymentStatus(Payments.PaymentStatus.valueOf(dto.getPaymentStatus().name()));
            } catch (IllegalArgumentException ex) {
                // ignore
            }
        }
        return e;
    }
}
