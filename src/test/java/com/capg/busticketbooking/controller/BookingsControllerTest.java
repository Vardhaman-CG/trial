package com.capg.busticketbooking.controller;

import com.capg.busticketbooking.dto.BookingsDTO;
import com.capg.busticketbooking.service.BookingsService;
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
public class BookingsControllerTest {

    private MockMvc mvc;
    private final ObjectMapper mapper = new ObjectMapper();

    @Mock
    private BookingsService service;

    @InjectMocks
    private BookingsController controller;

    @BeforeEach
    void setup() {
        mvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void getAll_shouldReturnList() throws Exception {
        BookingsDTO dto = new BookingsDTO(); dto.setBookingId(1); dto.setSeatNumber(5);
        when(service.getAll()).thenReturn(List.of(dto));

        mvc.perform(get("/api/bookings").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("[0].seatNumber").value(5));
    }

    @Test
    void getById_shouldReturnDto() throws Exception {
        BookingsDTO dto = new BookingsDTO(); dto.setBookingId(2); dto.setSeatNumber(10);
        when(service.getById(2)).thenReturn(dto);

        mvc.perform(get("/api/bookings/2").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath(".seatNumber").value(10));
    }

    @Test
    void create_shouldReturnOk() throws Exception {
        BookingsDTO in = new BookingsDTO(); in.setSeatNumber(3);
        BookingsDTO out = new BookingsDTO(); out.setBookingId(9); out.setSeatNumber(3);
        when(service.create(any())).thenReturn(out);

        mvc.perform(post("/api/bookings").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(in)))
                .andExpect(status().isOk())
                .andExpect(jsonPath(".bookingId").value(9));
    }
}
