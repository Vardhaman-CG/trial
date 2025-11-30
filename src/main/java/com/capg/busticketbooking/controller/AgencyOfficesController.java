package com.capg.busticketbooking.controller;

import com.capg.busticketbooking.dto.AgencyOfficesDTO;
import com.capg.busticketbooking.entity.Agency_Offices;
import com.capg.busticketbooking.mapper.AgencyOfficesMapper;
import com.capg.busticketbooking.repository.Agency_OfficesRepository;
import com.capg.busticketbooking.service.AgencyOfficesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping({"/api/agencies/offices"})
public class AgencyOfficesController {

    @Autowired
    private AgencyOfficesService agencyOfficesService;

    @Autowired
    private Agency_OfficesRepository repository;

    @PostMapping
    public ResponseEntity<AgencyOfficesDTO> create(@RequestBody AgencyOfficesDTO dto){
        return ResponseEntity.ok(agencyOfficesService.create(dto));
    }

    @PostMapping("/addoffice")
    public ResponseEntity<String> addOffice(@RequestBody AgencyOfficesDTO dto) {
        agencyOfficesService.create(dto);
        return ResponseEntity.ok("String: Record Created Successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<AgencyOfficesDTO> update(@PathVariable Integer id, @RequestBody AgencyOfficesDTO dto){
        AgencyOfficesDTO updated = agencyOfficesService.update(id, dto);
        if (updated == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id){
        agencyOfficesService.delete(id);
        return ResponseEntity.ok("String: Record Deleted Successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgencyOfficesDTO> getById(@PathVariable Integer id){
        AgencyOfficesDTO dto = agencyOfficesService.getById(id);
        if (dto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<AgencyOfficesDTO>> getAll(){
        return ResponseEntity.ok(agencyOfficesService.getAll());
    }

    @GetMapping("/agency/{agency_id}")
    public ResponseEntity<List<AgencyOfficesDTO>> getByAgency(@PathVariable("agency_id") Integer agencyId) {
        List<Agency_Offices> list = repository.findByAgency_AgencyId(agencyId);
        return ResponseEntity.ok(list.stream().map(AgencyOfficesMapper::toDTO).collect(Collectors.toList()));
    }
}
