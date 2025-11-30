package com.capg.busticketbooking.controller;

import com.capg.busticketbooking.dto.BusesDTO;
import com.capg.busticketbooking.entity.Buses;
import com.capg.busticketbooking.mapper.BusesMapper;
import com.capg.busticketbooking.repository.BusesRepository;
import com.capg.busticketbooking.service.BusesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping({"/api/buses"})
public class BusesController {

    @Autowired
    private BusesService busesService;

    @Autowired
    private BusesRepository busesRepository;

    @PostMapping
    public ResponseEntity<String> create(@RequestBody BusesDTO dto){
        busesService.create(dto);
        return ResponseEntity.ok("String: Record Created Successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<BusesDTO> update(@PathVariable Integer id, @RequestBody BusesDTO dto){
        BusesDTO updated = busesService.update(id, dto);
        if (updated == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id){
        busesService.delete(id);
        return ResponseEntity.ok("String: Record Deleted Successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<BusesDTO> getById(@PathVariable Integer id){
        BusesDTO dto = busesService.getById(id);
        if (dto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<BusesDTO>> getAll(){
        return ResponseEntity.ok(busesService.getAll());
    }

    @GetMapping("/office/{office_id}")
    public ResponseEntity<List<BusesDTO>> findByOffice(@PathVariable("office_id") Integer officeId) {
        List<Buses> all = busesRepository.findAll();
        List<Buses> list = all.stream().filter(b -> b.getAgencyOffice() != null && b.getAgencyOffice().getOfficeId().equals(officeId)).toList();
        return ResponseEntity.ok(list.stream().map(BusesMapper::toDTO).collect(Collectors.toList()));
    }
}
