package com.capg.busticketbooking.service.impl;

import com.capg.busticketbooking.dto.PaymentsDTO;
import com.capg.busticketbooking.entity.Bookings;
import com.capg.busticketbooking.entity.Customer;
import com.capg.busticketbooking.entity.Payments;
import com.capg.busticketbooking.mapper.PaymentsMapper;
import com.capg.busticketbooking.repository.BookingsRepository;
import com.capg.busticketbooking.repository.CustomerRepository;
import com.capg.busticketbooking.repository.PaymentsRepository;
import com.capg.busticketbooking.service.PaymentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PaymentsServiceImpl implements PaymentsService {

    @Autowired
    private PaymentsRepository paymentsRepository;

    @Autowired
    private BookingsRepository bookingsRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public PaymentsDTO create(PaymentsDTO dto) {
        Payments e = PaymentsMapper.toEntity(dto);
        // ensure we always persist a new Payments entity even if client sent an id
        e.setPaymentId(null);
        // fetch managed booking and customer
        if (dto.getBookingId() != null) {
            Bookings booking = bookingsRepository.findById(dto.getBookingId()).orElse(null);
            e.setBooking(booking);
        }
        if (dto.getCustomerId() != null) {
            Customer customer = customerRepository.findById(dto.getCustomerId()).orElse(null);
            e.setCustomer(customer);
        }
        // ensure payment date is set
        if (dto.getPaymentDate() != null) e.setPaymentDate(dto.getPaymentDate());
        Payments saved = paymentsRepository.save(e);
        return PaymentsMapper.toDTO(saved);
    }

    @Override
    public PaymentsDTO update(Integer id, PaymentsDTO dto) {
        Optional<Payments> opt = paymentsRepository.findById(id);
        if (!opt.isPresent()) return null;
        Payments existing = opt.get();
        existing.setAmount(dto.getAmount());
        if (dto.getPaymentDate() != null) existing.setPaymentDate(dto.getPaymentDate());
        // don't replace booking/customer here; updates should be explicit if required
        Payments saved = paymentsRepository.save(existing);
        return PaymentsMapper.toDTO(saved);
    }

    @Override
    public void delete(Integer id) {
        paymentsRepository.deleteById(id);
    }

    @Override
    public PaymentsDTO getById(Integer id) {
        return paymentsRepository.findById(id).map(PaymentsMapper::toDTO).orElse(null);
    }

    @Override
    public List<PaymentsDTO> getAll() {
        return paymentsRepository.findAll().stream().map(PaymentsMapper::toDTO).collect(Collectors.toList());
    }
}
