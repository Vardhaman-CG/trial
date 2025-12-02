package com.capg.busticketbooking.controller;

import com.capg.busticketbooking.dto.RoutesDTO;
import com.capg.busticketbooking.service.RoutesService;
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
public class RoutesControllerTest {

    private MockMvc mvc;
    private final ObjectMapper mapper = new ObjectMapper();

    @Mock
    private RoutesService service;

    @InjectMocks
    private RoutesController controller;

    @BeforeEach
    void setup() {
        mvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void getAll_shouldReturnList() throws Exception {
        RoutesDTO dto = new RoutesDTO(); dto.setRouteId(1); dto.setFromCity("CityA");
        when(service.getAll()).thenReturn(List.of(dto));

        mvc.perform(get("/api/routes").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("[0].fromCity").value("CityA"));
    }

    @Test
    void getById_shouldReturnDto() throws Exception {
        RoutesDTO dto = new RoutesDTO(); dto.setRouteId(2); dto.setFromCity("CityB");
        when(service.getById(2)).thenReturn(dto);

        mvc.perform(get("/api/routes/2").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath(".fromCity").value("CityB"));
    }

    @Test
    void create_shouldReturnOk() throws Exception {
        RoutesDTO in = new RoutesDTO(); in.setFromCity("NewR");
        RoutesDTO out = new RoutesDTO(); out.setRouteId(11); out.setFromCity("NewR");
        when(service.create(any())).thenReturn(out);

        mvc.perform(post("/api/routes").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(in)))
                .andExpect(status().isOk())
                .andExpect(jsonPath(".routeId").value(11));
    }
}
