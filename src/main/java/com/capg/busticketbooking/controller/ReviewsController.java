package com.capg.busticketbooking.controller;

import com.capg.busticketbooking.dto.ReviewsDTO;
import com.capg.busticketbooking.entity.Reviews;
import com.capg.busticketbooking.mapper.ReviewsMapper;
import com.capg.busticketbooking.repository.ReviewsRepository;
import com.capg.busticketbooking.service.ReviewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/reviews")
public class ReviewsController {

    @Autowired
    private ReviewsService reviewsService;

    @Autowired
    private ReviewsRepository reviewsRepository;

    @PostMapping
    public ResponseEntity<ReviewsDTO> create(@RequestBody ReviewsDTO dto){
        return ResponseEntity.ok(reviewsService.create(dto));
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
        List<Reviews> list = reviewsRepository.findByTrip_TripId(trip_id);
        return ResponseEntity.ok(list.stream().map(ReviewsMapper::toDTO).collect(Collectors.toList()));
    }

    @GetMapping("/office/{office_id}")
    public ResponseEntity<List<ReviewsDTO>> findByOffice(@PathVariable Integer office_id) {
        List<Reviews> list = reviewsRepository.findByOfficeId(office_id);
        return ResponseEntity.ok(list.stream().map(ReviewsMapper::toDTO).collect(Collectors.toList()));
    }

    @GetMapping("/customerid/{customer_id}")
    public ResponseEntity<List<ReviewsDTO>> findByCustomer(@PathVariable Integer customer_id) {
        List<Reviews> list = reviewsRepository.findByCustomer_CustomerId(customer_id);
        return ResponseEntity.ok(list.stream().map(ReviewsMapper::toDTO).collect(Collectors.toList()));
    }

    @GetMapping("/agency/{agency_id}")
    public ResponseEntity<List<ReviewsDTO>> findByAgency(@PathVariable Integer agency_id) {
        List<Reviews> list = reviewsRepository.findByAgencyId(agency_id);
        return ResponseEntity.ok(list.stream().map(ReviewsMapper::toDTO).collect(Collectors.toList()));
    }

    @GetMapping("/driver/{driver_id}")
    public ResponseEntity<List<ReviewsDTO>> findByDriver(@PathVariable Integer driver_id) {
        List<Reviews> list = reviewsRepository.findByDriverId(driver_id);
        return ResponseEntity.ok(list.stream().map(ReviewsMapper::toDTO).collect(Collectors.toList()));
    }
}
