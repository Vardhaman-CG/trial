package com.capg.busticketbooking.controller;

import com.capg.busticketbooking.dto.RoutesDTO;
import com.capg.busticketbooking.entity.Routes;
import com.capg.busticketbooking.mapper.RoutesMapper;
import com.capg.busticketbooking.repository.RoutesRepository;
import com.capg.busticketbooking.service.RoutesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/routes")
public class RoutesController {

    @Autowired
    private RoutesService routesService;

    @Autowired
    private RoutesRepository routesRepository;

    @PostMapping
    public ResponseEntity<RoutesDTO> create(@RequestBody RoutesDTO dto){
        return ResponseEntity.ok(routesService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoutesDTO> update(@PathVariable Integer id, @RequestBody RoutesDTO dto){
        RoutesDTO updated = routesService.update(id, dto);
        if (updated == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id){
        routesService.delete(id);
        return ResponseEntity.ok("String: Record Deleted Successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoutesDTO> getById(@PathVariable Integer id){
        RoutesDTO dto = routesService.getById(id);
        if (dto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<RoutesDTO>> getAll(){
        return ResponseEntity.ok(routesService.getAll());
    }

    // GET /api/routes/fromcity/{from_city}
    @GetMapping("/fromcity/{from_city}")
    public ResponseEntity<List<RoutesDTO>> findByFromCity(@PathVariable String from_city) {
        List<Routes> list = routesRepository.findByFromCityIgnoreCase(from_city);
        return ResponseEntity.ok(list.stream().map(RoutesMapper::toDTO).collect(Collectors.toList()));
    }

    // GET /api/routes/tocity/{to_city}
    @GetMapping("/tocity/{to_city}")
    public ResponseEntity<List<RoutesDTO>> findByToCity(@PathVariable String to_city) {
        List<Routes> list = routesRepository.findByToCityIgnoreCase(to_city);
        return ResponseEntity.ok(list.stream().map(RoutesMapper::toDTO).collect(Collectors.toList()));
    }

    // GET /api/routes/{from_city}/{to_city}
    @GetMapping("/{from_city}/{to_city}")
    public ResponseEntity<RoutesDTO> findByFromAndTo(@PathVariable String from_city, @PathVariable String to_city) {
        List<Routes> list = routesRepository.findByFromCityIgnoreCaseAndToCityIgnoreCase(from_city, to_city);
        if (list.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(RoutesMapper.toDTO(list.get(0)));
    }
}
