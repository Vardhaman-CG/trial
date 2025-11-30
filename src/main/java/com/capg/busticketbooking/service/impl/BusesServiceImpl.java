package com.capg.busticketbooking.service.impl;

import com.capg.busticketbooking.dto.BusesDTO;
import com.capg.busticketbooking.entity.Buses;
import com.capg.busticketbooking.entity.Agency_Offices;
import com.capg.busticketbooking.mapper.BusesMapper;
import com.capg.busticketbooking.repository.BusesRepository;
import com.capg.busticketbooking.repository.Agency_OfficesRepository;
import com.capg.busticketbooking.service.BusesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BusesServiceImpl implements BusesService {

    @Autowired
    private BusesRepository busesRepository;

    @Autowired
    private Agency_OfficesRepository agencyOfficesRepository;

    @Override
    public BusesDTO create(BusesDTO dto) {
        Buses e = BusesMapper.toEntity(dto);
        // ensure new insert
        e.setBusId(null);
        // set managed agency office if provided
        if (dto.getAgencyOfficeId() != null) {
            Optional<Agency_Offices> officeOpt = agencyOfficesRepository.findById(dto.getAgencyOfficeId());
            officeOpt.ifPresent(e::setAgencyOffice);
        }
        Buses saved = busesRepository.save(e);
        return BusesMapper.toDTO(saved);
    }

    @Override
    public BusesDTO update(Integer id, BusesDTO dto) {
        Optional<Buses> opt = busesRepository.findById(id);
        if (!opt.isPresent()) return null;
        Buses existing = opt.get();
        existing.setRegistrationNumber(dto.getRegistrationNumber());
        existing.setCapacity(dto.getCapacity());
        existing.setType(dto.getType());
        // update agency office if provided
        if (dto.getAgencyOfficeId() != null) {
            agencyOfficesRepository.findById(dto.getAgencyOfficeId()).ifPresent(existing::setAgencyOffice);
        }
        Buses saved = busesRepository.save(existing);
        return BusesMapper.toDTO(saved);
    }

    @Override
    public void delete(Integer id) {
        busesRepository.deleteById(id);
    }

    @Override
    public BusesDTO getById(Integer id) {
        return busesRepository.findById(id).map(BusesMapper::toDTO).orElse(null);
    }

    @Override
    public List<BusesDTO> getAll() {
        return busesRepository.findAll().stream().map(BusesMapper::toDTO).collect(Collectors.toList());
    }
}
