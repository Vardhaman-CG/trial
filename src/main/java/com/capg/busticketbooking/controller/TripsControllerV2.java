package com.capg.busticketbooking.controller;

import com.capg.busticketbooking.dto.TripsDTO;
import com.capg.busticketbooking.entity.Trips;
import com.capg.busticketbooking.service.TripsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/busticketbooking/trips-v2")
public class TripsControllerV2 {

    @Autowired
    private TripsService tripsService;

    @PostMapping
    public ResponseEntity<TripsDTO> create(@RequestBody TripsDTO dto){
        return ResponseEntity.ok(tripsService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TripsDTO> update(@PathVariable Integer id, @RequestBody TripsDTO dto){
        TripsDTO updated = tripsService.update(id, dto);
        if (updated == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        tripsService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TripsDTO> getById(@PathVariable Integer id){
        TripsDTO dto = tripsService.getById(id);
        if (dto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<java.util.List<TripsDTO>> getAll(){
        return ResponseEntity.ok(tripsService.getAll());
    }

    @GetMapping("/bus_type/{type}/trip_date/{trip_date}")
    public ResponseEntity<List<Trips>> getByTypeAndDate(@PathVariable String type, @PathVariable String trip_date){
        return ResponseEntity.ok(tripsService.getTripsByBusTypeAndDate(type, trip_date));
    }
}

