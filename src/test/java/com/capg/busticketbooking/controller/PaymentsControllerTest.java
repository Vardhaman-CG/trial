package com.capg.busticketbooking.controller;

import com.capg.busticketbooking.dto.PaymentsDTO;
import com.capg.busticketbooking.service.PaymentsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.containsString;

@ExtendWith(MockitoExtension.class)
public class PaymentsControllerTest {

    private MockMvc mvc;

    private final ObjectMapper mapper = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

    @Mock
    private PaymentsService paymentsService;

    @InjectMocks
    private PaymentsController controller;

    @BeforeEach
    void setup() {
        mvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void getAll_shouldReturnList() throws Exception {
        PaymentsDTO dto = new PaymentsDTO(1, null, null, 100.0, LocalDateTime.now(), PaymentsDTO.PaymentStatus.Success, null, null);
        when(paymentsService.getAll()).thenReturn(List.of(dto));

        mvc.perform(get("/api/payment").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("[0].amount").value(100.0));
    }

    @Test
    void getById_shouldReturnDto() throws Exception {
        PaymentsDTO dto = new PaymentsDTO(2, null, null, 200.0, LocalDateTime.now(), PaymentsDTO.PaymentStatus.Success, null, null);
        when(paymentsService.getById(2)).thenReturn(dto);

        mvc.perform(get("/api/payment/2").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath(".amount").value(200.0));
    }

    @Test
    void create_shouldReturnOk() throws Exception {
        PaymentsDTO input = new PaymentsDTO(null, null, null, 50.0, LocalDateTime.now(), PaymentsDTO.PaymentStatus.Success, null, null);
        PaymentsDTO created = new PaymentsDTO(10, null, null, 50.0, LocalDateTime.now(), PaymentsDTO.PaymentStatus.Success, null, null);
        when(paymentsService.create(any())).thenReturn(created);

        mvc.perform(post("/api/payment").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(input)))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Record Created Successfully")));
    }
}
