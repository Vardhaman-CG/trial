package com.capg.busticketbooking.controller;

import com.capg.busticketbooking.dto.AgenciesDTO;
import com.capg.busticketbooking.service.AgenciesService;
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
public class AgenciesControllerTest {

    private MockMvc mvc;
    private final ObjectMapper mapper = new ObjectMapper();

    @Mock
    private AgenciesService service;

    @InjectMocks
    private AgenciesController controller;

    @BeforeEach
    void setup() {
        mvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void getAll_shouldReturnList() throws Exception {
        AgenciesDTO dto = new AgenciesDTO(); dto.setAgencyId(1); dto.setName("A1");
        when(service.getAll()).thenReturn(List.of(dto));

        mvc.perform(get("/api/agencies").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("[0].name").value("A1"));
    }

    @Test
    void getById_shouldReturnDto() throws Exception {
        AgenciesDTO dto = new AgenciesDTO(); dto.setAgencyId(2); dto.setName("A2");
        when(service.getById(2)).thenReturn(dto);

        mvc.perform(get("/api/agencies/2").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath(".name").value("A2"));
    }

    @Test
    void create_shouldReturnOk() throws Exception {
        AgenciesDTO in = new AgenciesDTO(); in.setName("NewA");
        AgenciesDTO out = new AgenciesDTO(); out.setAgencyId(11); out.setName("NewA");
        when(service.create(any())).thenReturn(out);

        mvc.perform(post("/api/agencies").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(in)))
                .andExpect(status().isOk())
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.content().string(org.hamcrest.Matchers.containsString("Record Created Successfully")));
    }
}
