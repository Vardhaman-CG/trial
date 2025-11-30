package com.capg.busticketbooking.service.impl;

import com.capg.busticketbooking.dto.DriversDTO;
import com.capg.busticketbooking.entity.Drivers;
import com.capg.busticketbooking.mapper.DriversMapper;
import com.capg.busticketbooking.repository.DriversRepository;
import com.capg.busticketbooking.service.DriversService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DriversServiceImpl implements DriversService {

    @Autowired
    private DriversRepository driversRepository;

    @Override
    public DriversDTO create(DriversDTO dto) {
        Drivers e = DriversMapper.toEntity(dto);
        Drivers saved = driversRepository.save(e);
        return DriversMapper.toDTO(saved);
    }

    @Override
    public DriversDTO update(Integer id, DriversDTO dto) {
        Optional<Drivers> opt = driversRepository.findById(id);
        if (!opt.isPresent()) return null;
        Drivers existing = opt.get();
        existing.setLicenseNumber(dto.getLicenseNumber());
        existing.setName(dto.getName());
        existing.setPhone(dto.getPhone());
        Drivers saved = driversRepository.save(existing);
        return DriversMapper.toDTO(saved);
    }

    @Override
    public void delete(Integer id) {
        driversRepository.deleteById(id);
    }

    @Override
    public DriversDTO getById(Integer id) {
        return driversRepository.findById(id).map(DriversMapper::toDTO).orElse(null);
    }

    @Override
    public List<DriversDTO> getAll() {
        return driversRepository.findAll().stream().map(DriversMapper::toDTO).collect(Collectors.toList());
    }
}
