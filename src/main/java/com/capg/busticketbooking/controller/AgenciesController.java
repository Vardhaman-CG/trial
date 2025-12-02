package com.capg.busticketbooking.controller;

import com.capg.busticketbooking.dto.AgenciesDTO;
import com.capg.busticketbooking.service.AgenciesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/agencies")
public class AgenciesController {

    @Autowired
    private AgenciesService agenciesService;

    @PostMapping
    public ResponseEntity<String> create(@RequestBody AgenciesDTO dto){
        agenciesService.create(dto);
        return ResponseEntity.ok("String: Record Created Successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Integer id, @RequestBody AgenciesDTO dto){
        AgenciesDTO updated = agenciesService.update(id, dto);
        if (updated == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok("String: Record Updated Successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id){
        agenciesService.delete(id);
        return ResponseEntity.ok("String: Record Deleted Successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgenciesDTO> getById(@PathVariable Integer id){
        AgenciesDTO dto = agenciesService.getById(id);
        if (dto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<AgenciesDTO>> getAll(){
        return ResponseEntity.ok(agenciesService.getAll());
    }
}
