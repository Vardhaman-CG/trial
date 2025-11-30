package com.capg.busticketbooking.service.impl;

import com.capg.busticketbooking.dto.AgenciesDTO;
import com.capg.busticketbooking.entity.Agencies;
import com.capg.busticketbooking.mapper.AgenciesMapper;
import com.capg.busticketbooking.repository.AgenciesRepository;
import com.capg.busticketbooking.service.AgenciesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AgenciesServiceImpl implements AgenciesService {

    @Autowired
    private AgenciesRepository agenciesRepository;

    @Override
    public AgenciesDTO create(AgenciesDTO dto) {
        Agencies e = AgenciesMapper.toEntity(dto);
        Agencies saved = agenciesRepository.save(e);
        return AgenciesMapper.toDTO(saved);
    }

    @Override
    public AgenciesDTO update(Integer id, AgenciesDTO dto) {
        Optional<Agencies> opt = agenciesRepository.findById(id);
        if (!opt.isPresent()) return null;
        Agencies existing = opt.get();
        existing.setName(dto.getName());
        existing.setContactPersonName(dto.getContactPersonName());
        existing.setEmail(dto.getEmail());
        existing.setPhone(dto.getPhone());
        Agencies saved = agenciesRepository.save(existing);
        return AgenciesMapper.toDTO(saved);
    }

    @Override
    public void delete(Integer id) {
        agenciesRepository.deleteById(id);
    }

    @Override
    public AgenciesDTO getById(Integer id) {
        return agenciesRepository.findById(id).map(AgenciesMapper::toDTO).orElse(null);
    }

    @Override
    public List<AgenciesDTO> getAll() {
        return agenciesRepository.findAll().stream().map(AgenciesMapper::toDTO).collect(Collectors.toList());
    }
}

