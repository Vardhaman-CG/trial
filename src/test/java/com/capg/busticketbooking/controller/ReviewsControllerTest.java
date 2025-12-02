package com.capg.busticketbooking.controller;

import com.capg.busticketbooking.dto.ReviewsDTO;
import com.capg.busticketbooking.service.ReviewsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class ReviewsControllerTest {

    private MockMvc mvc;
    private final ObjectMapper mapper = new ObjectMapper();

    @Mock
    private ReviewsService service;

    @InjectMocks
    private ReviewsController controller;

    @BeforeEach
    void setup() {
        mvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void getAll_shouldReturnList() throws Exception {
        ReviewsDTO dto = new ReviewsDTO(); dto.setReviewId(1); dto.setRating(4);
        when(service.getAll()).thenReturn(List.of(dto));

        mvc.perform(get("/api/reviews").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("[0].rating").value(4));
    }

    @Test
    void getById_shouldReturnDto() throws Exception {
        ReviewsDTO dto = new ReviewsDTO(); dto.setReviewId(2); dto.setRating(5);
        when(service.getById(2)).thenReturn(dto);

        mvc.perform(get("/api/reviews/2").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath(".rating").value(5));
    }

    @Test
    void create_shouldReturnOk() throws Exception {
        ReviewsDTO in = new ReviewsDTO(); in.setRating(3);
        ReviewsDTO out = new ReviewsDTO(); out.setReviewId(11); out.setRating(3);
        when(service.create(any())).thenReturn(out);

        mvc.perform(post("/api/reviews").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(in)))
                .andExpect(status().isOk())
                .andExpect(jsonPath(".reviewId").value(11));
    }
}
