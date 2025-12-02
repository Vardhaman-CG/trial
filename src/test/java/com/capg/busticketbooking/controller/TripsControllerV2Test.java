package com.capg.busticketbooking.controller;

import com.capg.busticketbooking.dto.TripsDTO;
import com.capg.busticketbooking.service.TripsService;
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
public class TripsControllerV2Test {

    private MockMvc mvc;
    private final ObjectMapper mapper = new ObjectMapper();

    @Mock
    private TripsService service;

    @InjectMocks
    private TripsControllerV2 controller;

    @BeforeEach
    void setup() {
        mvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void getAll_shouldReturnList() throws Exception {
        TripsDTO dto = new TripsDTO(); dto.setTripId(1);
        when(service.getAll()).thenReturn(List.of(dto));

        mvc.perform(get("/api/busticketbooking/trips-v2").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("[0].tripId").value(1));
    }

    @Test
    void getById_shouldReturnDto() throws Exception {
        TripsDTO dto = new TripsDTO(); dto.setTripId(2);
        when(service.getById(2)).thenReturn(dto);

        mvc.perform(get("/api/busticketbooking/trips-v2/2").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath(".tripId").value(2));
    }

    @Test
    void create_shouldReturnOk() throws Exception {
        TripsDTO in = new TripsDTO();
        TripsDTO out = new TripsDTO(); out.setTripId(11);
        when(service.create(any())).thenReturn(out);

        mvc.perform(post("/api/busticketbooking/trips-v2").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(in)))
                .andExpect(status().isOk())
                .andExpect(jsonPath(".tripId").value(11));
    }
}
