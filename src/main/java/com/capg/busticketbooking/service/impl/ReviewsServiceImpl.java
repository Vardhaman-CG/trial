package com.capg.busticketbooking.service.impl;

import com.capg.busticketbooking.dto.ReviewsDTO;
import com.capg.busticketbooking.entity.Reviews;
import com.capg.busticketbooking.mapper.ReviewsMapper;
import com.capg.busticketbooking.repository.ReviewsRepository;
import com.capg.busticketbooking.service.ReviewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReviewsServiceImpl implements ReviewsService {

    @Autowired
    private ReviewsRepository reviewsRepository;

    @Override
    public ReviewsDTO create(ReviewsDTO dto) {
        Reviews e = ReviewsMapper.toEntity(dto);
        Reviews saved = reviewsRepository.save(e);
        return ReviewsMapper.toDTO(saved);
    }

    @Override
    public ReviewsDTO update(Integer id, ReviewsDTO dto) {
        Optional<Reviews> opt = reviewsRepository.findById(id);
        if (!opt.isPresent()) return null;
        Reviews existing = opt.get();
        existing.setRating(dto.getRating());
        existing.setComment(dto.getComment());
        Reviews saved = reviewsRepository.save(existing);
        return ReviewsMapper.toDTO(saved);
    }

    @Override
    public void delete(Integer id) {
        reviewsRepository.deleteById(id);
    }

    @Override
    public ReviewsDTO getById(Integer id) {
        return reviewsRepository.findById(id).map(ReviewsMapper::toDTO).orElse(null);
    }

    @Override
    public List<ReviewsDTO> getAll() {
        return reviewsRepository.findAll().stream().map(ReviewsMapper::toDTO).collect(Collectors.toList());
    }
}

