package com.capg.busticketbooking.controller;

import com.capg.busticketbooking.dto.AddressesDTO;
import com.capg.busticketbooking.service.AddressesService;
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
public class AddressesControllerTest {

    private MockMvc mvc;
    private final ObjectMapper mapper = new ObjectMapper();

    @Mock
    private AddressesService service;

    @InjectMocks
    private AddressesController controller;

    @BeforeEach
    void setup() {
        mvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void getAll_shouldReturnList() throws Exception {
        AddressesDTO dto = new AddressesDTO(); dto.setAddressId(1); dto.setCity("CityX");
        when(service.getAll()).thenReturn(List.of(dto));

        mvc.perform(get("/api/addresses").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("[0].city").value("CityX"));
    }

    @Test
    void getById_shouldReturnDto() throws Exception {
        AddressesDTO dto = new AddressesDTO(); dto.setAddressId(2); dto.setCity("CityY");
        when(service.getById(2)).thenReturn(dto);

        mvc.perform(get("/api/addresses/2").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath(".city").value("CityY"));
    }

    @Test
    void create_shouldReturnOk() throws Exception {
        AddressesDTO in = new AddressesDTO(); in.setCity("NewCity");
        AddressesDTO out = new AddressesDTO(); out.setAddressId(11); out.setCity("NewCity");
        when(service.create(any())).thenReturn(out);

        mvc.perform(post("/api/addresses").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(in)))
                .andExpect(status().isOk())
                .andExpect(jsonPath(".addressId").value(11));
    }
}
