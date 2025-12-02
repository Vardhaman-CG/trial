package com.capg.busticketbooking.controller;

import com.capg.busticketbooking.dto.CustomerDTO;
import com.capg.busticketbooking.service.CustomerService;
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
public class CustomerControllerTest {

    private MockMvc mvc;

    private final ObjectMapper mapper = new ObjectMapper();

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController controller;

    @BeforeEach
    void setup() {
        mvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void getAll_shouldReturnList() throws Exception {
        CustomerDTO dto = CustomerDTO.builder().customerId(1).name("Alice").email("a@x.com").phone("123").build();
        when(customerService.getAll()).thenReturn(List.of(dto));

        mvc.perform(get("/api/customers").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("[0].name").value("Alice"));
    }

    @Test
    void getById_shouldReturnDto() throws Exception {
        CustomerDTO dto = CustomerDTO.builder().customerId(1).name("Bob").email("b@x.com").phone("456").build();
        when(customerService.getById(1)).thenReturn(dto);

        mvc.perform(get("/api/customers/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath(".name").value("Bob"));
    }

    @Test
    void create_shouldReturnCreatedDto() throws Exception {
        CustomerDTO input = CustomerDTO.builder().name("New").email("n@x.com").phone("789").build();
        CustomerDTO returned = CustomerDTO.builder().customerId(5).name("New").email("n@x.com").phone("789").build();
        when(customerService.create(any())).thenReturn(returned);

        mvc.perform(post("/api/customers").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(input)))
                .andExpect(status().isOk())
                .andExpect(jsonPath(".customerId").value(5));
    }
}
