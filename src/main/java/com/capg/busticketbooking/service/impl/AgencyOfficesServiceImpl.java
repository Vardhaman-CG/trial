package com.capg.busticketbooking.service.impl;

import com.capg.busticketbooking.dto.AgencyOfficesDTO;
import com.capg.busticketbooking.entity.Agency_Offices;
import com.capg.busticketbooking.mapper.AgencyOfficesMapper;
import com.capg.busticketbooking.repository.Agency_OfficesRepository;
import com.capg.busticketbooking.service.AgencyOfficesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AgencyOfficesServiceImpl implements AgencyOfficesService {

    @Autowired
    private Agency_OfficesRepository repository;

    @Override
    public AgencyOfficesDTO create(AgencyOfficesDTO dto) {
        Agency_Offices e = AgencyOfficesMapper.toEntity(dto);
        Agency_Offices saved = repository.save(e);
        return AgencyOfficesMapper.toDTO(saved);
    }

    @Override
    public AgencyOfficesDTO update(Integer id, AgencyOfficesDTO dto) {
        Optional<Agency_Offices> opt = repository.findById(id);
        if (!opt.isPresent()) return null;
        Agency_Offices existing = opt.get();
        existing.setOfficeMail(dto.getOfficeMail());
        existing.setOfficeContactPersonName(dto.getOfficeContactPersonName());
        existing.setOfficeContactNumber(dto.getOfficeContactNumber());
        Agency_Offices saved = repository.save(existing);
        return AgencyOfficesMapper.toDTO(saved);
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public AgencyOfficesDTO getById(Integer id) {
        return repository.findById(id).map(AgencyOfficesMapper::toDTO).orElse(null);
    }

    @Override
    public List<AgencyOfficesDTO> getAll() {
        return repository.findAll().stream().map(AgencyOfficesMapper::toDTO).collect(Collectors.toList());
    }
}

