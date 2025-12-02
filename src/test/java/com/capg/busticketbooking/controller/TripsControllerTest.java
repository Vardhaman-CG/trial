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
public class TripsControllerTest {

    private MockMvc mvc;
    private final ObjectMapper mapper = new ObjectMapper();

    @Mock
    private TripsService service;

    @InjectMocks
    private TripsController controller;

    @BeforeEach
    void setup() {
        mvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void getAll_shouldReturnList() throws Exception {
        TripsDTO dto = new TripsDTO(); dto.setTripId(1);
        when(service.getAll()).thenReturn(List.of(dto));

        mvc.perform(get("/api/trips").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("[0].tripId").value(1));
    }

    @Test
    void getById_shouldReturnDto() throws Exception {
        TripsDTO dto = new TripsDTO(); dto.setTripId(2);
        when(service.getById(2)).thenReturn(dto);

        mvc.perform(get("/api/trips/2").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath(".tripId").value(2));
    }

    @Test
    void create_shouldReturnOk() throws Exception {
        // TripsDTO has several fields annotated WRITE_ONLY so serializing the DTO instance
        // will omit them. Build an explicit JSON payload instead.
        java.util.Map<String, Object> payload = java.util.Map.of(
                "boardingAddressId", 1,
                "droppingAddressId", 2,
                "busId", 1
        );
        TripsDTO out = new TripsDTO(); out.setTripId(11);
        when(service.create(any())).thenReturn(out);

        mvc.perform(post("/api/trips").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(payload)))
                .andExpect(status().isOk())
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.content().string(org.hamcrest.Matchers.containsString("Record Created Successfully")));
    }
}
