package com.capg.busticketbooking.controller;

import com.capg.busticketbooking.dto.BusesDTO;
import com.capg.busticketbooking.service.BusesService;
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
public class BusesControllerTest {

    private MockMvc mvc;
    private final ObjectMapper mapper = new ObjectMapper();

    @Mock
    private BusesService service;

    @InjectMocks
    private BusesController controller;

    @BeforeEach
    void setup() {
        mvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void getAll_shouldReturnList() throws Exception {
        BusesDTO dto = new BusesDTO(); dto.setBusId(1); dto.setRegistrationNumber("REG123");
        when(service.getAll()).thenReturn(List.of(dto));

        mvc.perform(get("/api/buses").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("[0].registrationNumber").value("REG123"));
    }

    @Test
    void getById_shouldReturnDto() throws Exception {
        BusesDTO dto = new BusesDTO(); dto.setBusId(2); dto.setRegistrationNumber("R2");
        when(service.getById(2)).thenReturn(dto);

        mvc.perform(get("/api/buses/2").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath(".registrationNumber").value("R2"));
    }

    @Test
    void create_shouldReturnOk() throws Exception {
        BusesDTO in = new BusesDTO(); in.setRegistrationNumber("NEW"); in.setAgencyOfficeId(1);
        BusesDTO out = new BusesDTO(); out.setBusId(7); out.setRegistrationNumber("NEW"); out.setAgencyOfficeId(1);
        when(service.create(any())).thenReturn(out);

        mvc.perform(post("/api/buses").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(in)))
                .andExpect(status().isOk())
                .andExpect(jsonPath(".busId").value(7));
    }
}
