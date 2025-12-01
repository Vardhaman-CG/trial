package com.capg.busticketbooking.service.impl;

import com.capg.busticketbooking.dto.RoutesDTO;
import com.capg.busticketbooking.entity.Routes;
import com.capg.busticketbooking.mapper.RoutesMapper;
import com.capg.busticketbooking.repository.RoutesRepository;
import com.capg.busticketbooking.service.RoutesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoutesServiceImpl implements RoutesService {

    @Autowired
    private RoutesRepository routesRepository;

    @Override
    public RoutesDTO create(RoutesDTO dto) {
        Routes e = RoutesMapper.toEntity(dto);
        Routes saved = routesRepository.save(e);
        return RoutesMapper.toDTO(saved);
    }

    @Override
    public RoutesDTO update(Integer id, RoutesDTO dto) {
        Optional<Routes> opt = routesRepository.findById(id);
        if (!opt.isPresent()) return null;
        Routes existing = opt.get();
        existing.setFromCity(dto.getFromCity());
        existing.setToCity(dto.getToCity());
        existing.setBreakPoints(dto.getBreakPoints());
        existing.setDuration(dto.getDuration());
        Routes saved = routesRepository.save(existing);
        return RoutesMapper.toDTO(saved);
    }

    @Override
    public void delete(Integer id) {
        routesRepository.deleteById(id);
    }

    @Override
    public RoutesDTO getById(Integer id) {
        return routesRepository.findById(id).map(RoutesMapper::toDTO).orElse(null);
    }

        @Override
        public List<RoutesDTO> getAll() {
            return routesRepository.findAll().stream().map(RoutesMapper::toDTO).collect(Collectors.toList());
        }
}

