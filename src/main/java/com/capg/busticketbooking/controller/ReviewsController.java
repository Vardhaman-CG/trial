package com.capg.busticketbooking.controller;

import com.capg.busticketbooking.dto.ReviewsDTO;
import com.capg.busticketbooking.service.ReviewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewsController {

    @Autowired
    private ReviewsService reviewsService;

    @PostMapping
    public ResponseEntity<ReviewsDTO> create(@RequestBody ReviewsDTO dto){
        ReviewsDTO created = reviewsService.create(dto);
        if (created == null) return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReviewsDTO> update(@PathVariable Integer id, @RequestBody ReviewsDTO dto){
        ReviewsDTO updated = reviewsService.update(id, dto);
        if (updated == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id){
        reviewsService.delete(id);
        return ResponseEntity.ok("String: Record Deleted Successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewsDTO> getById(@PathVariable Integer id){
        ReviewsDTO dto = reviewsService.getById(id);
        if (dto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<ReviewsDTO>> getAll(){
        return ResponseEntity.ok(reviewsService.getAll());
    }

    // CSV searches
    @GetMapping("/tripid/{trip_id}")
    public ResponseEntity<List<ReviewsDTO>> findByTrip(@PathVariable Integer trip_id) {
        List<ReviewsDTO> list = reviewsService.getAll().stream().filter(r->r.getTripId()!=null && r.getTripId().equals(trip_id)).toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/office/{office_id}")
    public ResponseEntity<List<ReviewsDTO>> findByOffice(@PathVariable Integer office_id) {
        List<ReviewsDTO> list = reviewsService.getAll().stream().filter(r->false).toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/customerid/{customer_id}")
    public ResponseEntity<List<ReviewsDTO>> findByCustomer(@PathVariable Integer customer_id) {
        List<ReviewsDTO> list = reviewsService.getAll().stream().filter(r->r.getCustomerId()!=null && r.getCustomerId().equals(customer_id)).toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/agency/{agency_id}")
    public ResponseEntity<List<ReviewsDTO>> findByAgency(@PathVariable Integer agency_id) {
        List<ReviewsDTO> list = reviewsService.getAll().stream().filter(r->false).toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/driver/{driver_id}")
    public ResponseEntity<List<ReviewsDTO>> findByDriver(@PathVariable Integer driver_id) {
        List<ReviewsDTO> list = reviewsService.getAll().stream().filter(r->false).toList();
        return ResponseEntity.ok(list);
    }
}
