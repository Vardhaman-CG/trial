package com.capg.busticketbooking.controller;

import com.capg.busticketbooking.dto.BookingsDTO;
import com.capg.busticketbooking.service.BookingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingsController {

    @Autowired
    private BookingsService bookingsService;

    @PostMapping
    public ResponseEntity<BookingsDTO> create(@RequestBody BookingsDTO dto){
        return ResponseEntity.ok(bookingsService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookingsDTO> update(@PathVariable Integer id, @RequestBody BookingsDTO dto){
        BookingsDTO updated = bookingsService.update(id, dto);
        if (updated == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        bookingsService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingsDTO> getById(@PathVariable Integer id){
        BookingsDTO dto = bookingsService.getById(id);
        if (dto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<BookingsDTO>> getAll(){
        return ResponseEntity.ok(bookingsService.getAll());
    }
}

