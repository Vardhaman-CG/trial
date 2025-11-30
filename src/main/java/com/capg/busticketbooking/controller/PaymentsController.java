package com.capg.busticketbooking.controller;

import com.capg.busticketbooking.dto.PaymentsDTO;
import com.capg.busticketbooking.entity.Payments;
import com.capg.busticketbooking.mapper.PaymentsMapper;
import com.capg.busticketbooking.repository.PaymentsRepository;
import com.capg.busticketbooking.service.PaymentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping({"/api/payment"})
public class PaymentsController {

    @Autowired
    private PaymentsService paymentsService;

    @Autowired
    private PaymentsRepository paymentsRepository;

    @PostMapping
    public ResponseEntity<String> create(@RequestBody PaymentsDTO dto){
        paymentsService.create(dto);
        return ResponseEntity.ok("String: Record Created Successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaymentsDTO> update(@PathVariable Integer id, @RequestBody PaymentsDTO dto){
        PaymentsDTO updated = paymentsService.update(id, dto);
        if (updated == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id){
        paymentsService.delete(id);
        return ResponseEntity.ok("String: Record Deleted Successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentsDTO> getById(@PathVariable Integer id){
        PaymentsDTO dto = paymentsService.getById(id);
        if (dto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<PaymentsDTO>> getAll(){
        return ResponseEntity.ok(paymentsService.getAll());
    }

    @GetMapping("/office/{office_id}")
    public ResponseEntity<List<PaymentsDTO>> findByOffice(@PathVariable("office_id") Integer officeId) {
        List<Payments> list = paymentsRepository.findByOfficeId(officeId);
        return ResponseEntity.ok(list.stream().map(PaymentsMapper::toDTO).collect(Collectors.toList()));
    }

    @GetMapping("/agency/{agency_id}")
    public ResponseEntity<List<PaymentsDTO>> findByAgency(@PathVariable("agency_id") Integer agencyId) {
        List<Payments> list = paymentsRepository.findByAgencyId(agencyId);
        return ResponseEntity.ok(list.stream().map(PaymentsMapper::toDTO).collect(Collectors.toList()));
    }

    @GetMapping("/customer/{customer_id}")
    public ResponseEntity<List<PaymentsDTO>> findByCustomer(@PathVariable("customer_id") Integer customerId) {
        List<Payments> list = paymentsRepository.findByCustomer_CustomerId(customerId);
        return ResponseEntity.ok(list.stream().map(PaymentsMapper::toDTO).collect(Collectors.toList()));
    }
}
