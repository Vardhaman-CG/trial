package com.capg.busticketbooking.service.impl;

import com.capg.busticketbooking.dto.AddressesDTO;
import com.capg.busticketbooking.entity.Addresses;
import com.capg.busticketbooking.mapper.AddressesMapper;
import com.capg.busticketbooking.repository.AddressesRepository;
import com.capg.busticketbooking.service.AddressesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AddressesServiceImpl implements AddressesService {

    @Autowired
    private AddressesRepository addressesRepository;

    @Override
    public AddressesDTO create(AddressesDTO dto) {
        Addresses entity = AddressesMapper.toEntity(dto);
        Addresses saved = addressesRepository.save(entity);
        return AddressesMapper.toDTO(saved);
    }

    @Override
    public AddressesDTO update(Integer id, AddressesDTO dto) {
        Optional<Addresses> opt = addressesRepository.findById(id);
        if (!opt.isPresent()) return null;
        Addresses existing = opt.get();
        existing.setAddress(dto.getAddress());
        existing.setCity(dto.getCity());
        existing.setState(dto.getState());
        existing.setZipCode(dto.getZipCode());
        Addresses saved = addressesRepository.save(existing);
        return AddressesMapper.toDTO(saved);
    }

    @Override
    public void delete(Integer id) {
        addressesRepository.deleteById(id);
    }

    @Override
    public AddressesDTO getById(Integer id) {
        return addressesRepository.findById(id).map(AddressesMapper::toDTO).orElse(null);
    }

    @Override
    public List<AddressesDTO> getAll() {
        return addressesRepository.findAll().stream().map(AddressesMapper::toDTO).collect(Collectors.toList());
    }
}

