package com.capg.busticketbooking.controller;
import com.capg.busticketbooking.dto.TripsDTO;
import com.capg.busticketbooking.entity.Trips;
import com.capg.busticketbooking.mapper.TripsMapper;
import com.capg.busticketbooking.repository.TripsRepository;
import com.capg.busticketbooking.service.TripsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/trips")
public class TripsController {

    @Autowired
    private TripsService tripsService;

    @Autowired
    private TripsRepository tripsRepository;

    @GetMapping("/bus_type/{type}/trip_date/{trip_date}")
    public List<Trips> getTripsByBusTypeAndDate(
            @PathVariable("type") String type,
            @PathVariable("trip_date") String tripDate) {

        // tripDate should be provided as YYYY-MM-DD and forwarded to the service/repository
        return tripsService.getTripsByBusTypeAndDate(type, tripDate);
    }

    @GetMapping
    public ResponseEntity<List<TripsDTO>> getAll() {
        return ResponseEntity.ok(tripsService.getAll());
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody TripsDTO dto) {
        // validate mandatory foreign-key IDs to avoid DB constraint errors
        List<String> missing = new ArrayList<>();
        if (dto.getBoardingAddressId() == null) missing.add("boardingAddressId");
        if (dto.getDroppingAddressId() == null) missing.add("droppingAddressId");
        if (dto.getBusId() == null) missing.add("busId");
        if (!missing.isEmpty()) {
            return ResponseEntity.badRequest().body("Missing required fields: " + String.join(", ", missing));
        }

        tripsService.create(dto);
        return ResponseEntity.ok("String: Record Created Successfully");
    }

    @PutMapping
    public ResponseEntity<String> update(@RequestBody TripsDTO dto) {
        if (dto.getTripId() == null) return ResponseEntity.badRequest().body("Missing id");
        TripsDTO updated = tripsService.update(dto.getTripId(), dto);
        if (updated == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok("String: Record Updated Successfully");
    }

    @DeleteMapping("/{trip_id}")
    public ResponseEntity<String> delete(@PathVariable("trip_id") Integer id) {
        tripsService.delete(id);
        return ResponseEntity.ok("String: Record Deleted Successfully");
    }

    @GetMapping("/{trip_id}")
    public ResponseEntity<TripsDTO> getById(@PathVariable("trip_id") Integer id) {
        TripsDTO dto = tripsService.getById(id);
        if (dto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/from_city/{from_city}")
    public ResponseEntity<List<TripsDTO>> findByFromCity(@PathVariable String from_city) {
        List<Trips> list = tripsRepository.findByBoardingAddress_CityIgnoreCase(from_city);
        return ResponseEntity.ok(list.stream().map(TripsMapper::toDTO).collect(Collectors.toList()));
    }

    @GetMapping("/to_city/{to_city}")
    public ResponseEntity<List<TripsDTO>> findByToCity(@PathVariable String to_city) {
        List<Trips> list = tripsRepository.findByDroppingAddress_CityIgnoreCase(to_city);
        return ResponseEntity.ok(list.stream().map(TripsMapper::toDTO).collect(Collectors.toList()));
    }

    @GetMapping("/trip_date/{trip_date}")
    public ResponseEntity<List<TripsDTO>> findByTripDate(@PathVariable String trip_date) {
        List<Trips> list = tripsRepository.findByTripDate(trip_date);
        return ResponseEntity.ok(list.stream().map(TripsMapper::toDTO).collect(Collectors.toList()));
    }

    @GetMapping("/bus_type/{type}")
    public ResponseEntity<List<TripsDTO>> findByBusType(@PathVariable String type) {
        List<Trips> all = tripsRepository.findAll();
        List<Trips> filtered = all.stream().filter(t -> t.getBus() != null && type.equalsIgnoreCase(t.getBus().getType())).toList();
        return ResponseEntity.ok(filtered.stream().map(TripsMapper::toDTO).collect(Collectors.toList()));
    }

    @GetMapping("/{from_city}/{to_city}/{trip_date}")
    public ResponseEntity<List<TripsDTO>> findByFromToDate(@PathVariable String from_city, @PathVariable String to_city, @PathVariable String trip_date) {
        List<Trips> list = tripsRepository.findByFromToAndDate(from_city, to_city, trip_date);
        return ResponseEntity.ok(list.stream().map(TripsMapper::toDTO).collect(Collectors.toList()));
    }

    @GetMapping("/{from_city}/{to_city}/{trip_date}/{bus_type}")
    public ResponseEntity<List<TripsDTO>> findByFromToDateAndType(@PathVariable String from_city, @PathVariable String to_city, @PathVariable String trip_date, @PathVariable String bus_type) {
        List<Trips> list = tripsRepository.findByFromToDateAndBusType(from_city, to_city, trip_date, bus_type);
        return ResponseEntity.ok(list.stream().map(TripsMapper::toDTO).collect(Collectors.toList()));
    }

    // CSV special path
    @GetMapping("/store/bus_type/{type}/trip_date/{trip_date}")
    public ResponseEntity<List<TripsDTO>> storeSearchByTypeAndDate(@PathVariable String type, @PathVariable String trip_date) {
        List<Trips> list = tripsRepository.findByBusTypeAndTripDate(type, trip_date);
        return ResponseEntity.ok(list.stream().map(TripsMapper::toDTO).collect(Collectors.toList()));
    }
}