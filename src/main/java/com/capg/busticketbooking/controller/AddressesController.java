package com.capg.busticketbooking.controller;

import com.capg.busticketbooking.dto.AddressesDTO;
import com.capg.busticketbooking.service.AddressesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/addresses")
public class AddressesController {

    @Autowired
    private AddressesService addressesService;

    @PostMapping
    public ResponseEntity<AddressesDTO> create(@RequestBody AddressesDTO dto){
        return ResponseEntity.ok(addressesService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AddressesDTO> update(@PathVariable Integer id, @RequestBody AddressesDTO dto){
        AddressesDTO updated = addressesService.update(id, dto);
        if (updated == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        addressesService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressesDTO> getById(@PathVariable Integer id){
        AddressesDTO dto = addressesService.getById(id);
        if (dto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<AddressesDTO>> getAll(){
        return ResponseEntity.ok(addressesService.getAll());
    }
}

