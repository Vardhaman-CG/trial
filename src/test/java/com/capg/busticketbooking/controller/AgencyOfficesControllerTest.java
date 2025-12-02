package com.capg.busticketbooking.controller;

import com.capg.busticketbooking.dto.AgencyOfficesDTO;
import com.capg.busticketbooking.service.AgencyOfficesService;
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
public class AgencyOfficesControllerTest {

    private MockMvc mvc;
    private final ObjectMapper mapper = new ObjectMapper();

    @Mock
    private AgencyOfficesService service;

    @InjectMocks
    private AgencyOfficesController controller;

    @BeforeEach
    void setup() {
        mvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void getAll_shouldReturnList() throws Exception {
        AgencyOfficesDTO dto = new AgencyOfficesDTO(); dto.setOfficeId(1); dto.setOfficeMail("o@mail");
        when(service.getAll()).thenReturn(List.of(dto));

        mvc.perform(get("/api/agencies/offices").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("[0].officeMail").value("o@mail"));
    }

    @Test
    void getById_shouldReturnDto() throws Exception {
        AgencyOfficesDTO dto = new AgencyOfficesDTO(); dto.setOfficeId(2); dto.setOfficeMail("m@x");
        when(service.getById(2)).thenReturn(dto);

        mvc.perform(get("/api/agencies/offices/2").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath(".officeMail").value("m@x"));
    }

    @Test
    void create_shouldReturnOk() throws Exception {
        AgencyOfficesDTO in = new AgencyOfficesDTO(); in.setOfficeMail("new@x");
        AgencyOfficesDTO out = new AgencyOfficesDTO(); out.setOfficeId(11); out.setOfficeMail("new@x");
        when(service.create(any())).thenReturn(out);

        mvc.perform(post("/api/agencies/offices").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(in)))
                .andExpect(status().isOk())
                .andExpect(jsonPath(".officeId").value(11));
    }
}
