package com.capg.busticketbooking.controller;

import com.capg.busticketbooking.dto.CustomerDTO;
import com.capg.busticketbooking.entity.Customer;
import com.capg.busticketbooking.mapper.CustomerMapper;
import com.capg.busticketbooking.repository.CustomerRepository;
import com.capg.busticketbooking.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping({"/api/customers"})
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping
    public ResponseEntity<String> create(@RequestBody CustomerDTO dto){
        customerService.create(dto);
        return ResponseEntity.ok("String: Record Created Successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> update(@PathVariable Integer id, @RequestBody CustomerDTO dto){
        CustomerDTO updated = customerService.update(id, dto);
        if (updated == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updated);
    }

    // Keep original delete by id
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id){
        customerService.delete(id);
        return ResponseEntity.ok("String: Record Deleted Successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getById(@PathVariable Integer id){
        CustomerDTO dto = customerService.getById(id);
        if (dto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getAll(){
        return ResponseEntity.ok(customerService.getAll());
    }

    // Additional CSV endpoints:
    // PUT /api/customers (update using body with id) -> return success string
    @PutMapping
    public ResponseEntity<String> updateWhole(@RequestBody CustomerDTO dto) {
        if (dto.getCustomerId() == null) return ResponseEntity.badRequest().body("Missing id");
        CustomerDTO updated = customerService.update(dto.getCustomerId(), dto);
        if (updated == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok("String: Record Updated Successfully");
    }

    // PUT /api/customers/update/{id}/email
    @PutMapping("/update/{id}/email")
    public ResponseEntity<?> updateEmail(@PathVariable Integer id, @RequestBody String email) {
        CustomerDTO existing = customerService.getById(id);
        if (existing == null) return ResponseEntity.notFound().build();
        existing.setEmail(email.replaceAll("\"", ""));
        CustomerDTO updated = customerService.update(id, existing);
        return ResponseEntity.ok(updated);
    }

    // PUT /api/customers/update/{id}/ln -> update last name (assumes name field holds full name)
    @PutMapping("/update/{id}/ln")
    public ResponseEntity<?> updateLastName(@PathVariable Integer id, @RequestBody String lastName) {
        CustomerDTO existing = customerService.getById(id);
        if (existing == null) return ResponseEntity.notFound().build();
        String name = existing.getName() == null ? "" : existing.getName();
        String[] parts = name.split(" ");
        if (parts.length == 0) {
            existing.setName(lastName.replaceAll("\"", ""));
        } else {
            parts[parts.length - 1] = lastName.replaceAll("\"", "");
            existing.setName(String.join(" ", parts));
        }
        CustomerDTO updated = customerService.update(id, existing);
        return ResponseEntity.ok(updated);
    }

    // GET /api/customers/email/{email}
    @GetMapping("/email/{email}")
    public ResponseEntity<List<CustomerDTO>> findByEmail(@PathVariable String email) {
        List<Customer> list = customerRepository.findByEmailContainingIgnoreCase(email);
        return ResponseEntity.ok(list.stream().map(CustomerMapper::toDTO).collect(Collectors.toList()));
    }

    // GET /api/customers/phone/{phone}
    @GetMapping("/phone/{phone}")
    public ResponseEntity<List<CustomerDTO>> findByPhone(@PathVariable String phone) {
        List<Customer> list = customerRepository.findByPhoneContaining(phone);
        return ResponseEntity.ok(list.stream().map(CustomerMapper::toDTO).collect(Collectors.toList()));
    }

    // GET /api/customers/city/{city}
    @GetMapping("/city/{city}")
    public ResponseEntity<List<CustomerDTO>> findByCity(@PathVariable String city) {
        List<Customer> list = customerRepository.findByAddress_CityIgnoreCase(city);
        return ResponseEntity.ok(list.stream().map(CustomerMapper::toDTO).collect(Collectors.toList()));
    }

    // GET /api/customers/state/{state}
    @GetMapping("/state/{state}")
    public ResponseEntity<List<CustomerDTO>> findByState(@PathVariable String state) {
        List<Customer> list = customerRepository.findByAddress_StateIgnoreCase(state);
        return ResponseEntity.ok(list.stream().map(CustomerMapper::toDTO).collect(Collectors.toList()));
    }
}
