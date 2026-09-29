package com.uniguide.controller;

import com.uniguide.dto.HostelResponse;
import com.uniguide.exception.GlobalExceptionHandler;
import com.uniguide.exception.ResourceNotFoundException;
import com.uniguide.service.HostelService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class HostelControllerTest {

    private MockMvc mockMvc;

    @Mock
    private HostelService hostelService;

    @InjectMocks
    private HostelController hostelController;

    private HostelResponse sampleHostel;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(hostelController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();

        sampleHostel = HostelResponse.builder()
                .id(1L)
                .name("Kaveri Boys Hostel")
                .type("BOYS")
                .capacity(250)
                .wardenName("Dr. R. Sharma")
                .wardenContact("+1-555-400-5000")
                .description("Undergraduate residential hall with Wi-Fi and mess facilities.")
                .locationId(5L)
                .locationName("North Campus Hostel Zone")
                .build();
    }

    @Test
    @DisplayName("GET /api/hostels returns all hostels")
    void getHostels_ReturnsAll() throws Exception {
        when(hostelService.getAllHostels()).thenReturn(List.of(sampleHostel));

        mockMvc.perform(get("/api/hostels")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("Kaveri Boys Hostel")))
                .andExpect(jsonPath("$[0].type", is("BOYS")))
                .andExpect(jsonPath("$[0].capacity", is(250)));

        verify(hostelService).getAllHostels();
    }

    @Test
    @DisplayName("GET /api/hostels?type=BOYS returns hostels of specified type")
    void getHostels_TypeFilter() throws Exception {
        when(hostelService.getHostelsByType("BOYS")).thenReturn(List.of(sampleHostel));

        mockMvc.perform(get("/api/hostels")
                        .param("type", "BOYS")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].type", is("BOYS")));

        verify(hostelService).getHostelsByType("BOYS");
    }

    @Test
    @DisplayName("GET /api/hostels?locationId=5 returns hostels in specified location")
    void getHostels_LocationFilter() throws Exception {
        when(hostelService.getHostelsByLocation(5L)).thenReturn(List.of(sampleHostel));

        mockMvc.perform(get("/api/hostels")
                        .param("locationId", "5")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].locationId", is(5)));

        verify(hostelService).getHostelsByLocation(5L);
    }

    @Test
    @DisplayName("GET /api/hostels/{id} returns hostel when found")
    void getHostelById_Success() throws Exception {
        when(hostelService.getHostelById(1L)).thenReturn(sampleHostel);

        mockMvc.perform(get("/api/hostels/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Kaveri Boys Hostel")))
                .andExpect(jsonPath("$.wardenName", is("Dr. R. Sharma")));

        verify(hostelService).getHostelById(1L);
    }

    @Test
    @DisplayName("GET /api/hostels/{id} returns 404 when not found")
    void getHostelById_NotFound() throws Exception {
        when(hostelService.getHostelById(99L))
                .thenThrow(new ResourceNotFoundException("Hostel", "id", 99L));

        mockMvc.perform(get("/api/hostels/99")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status", is(404)))
                .andExpect(jsonPath("$.error", is("Not Found")))
                .andExpect(jsonPath("$.message", is("Hostel not found with id: '99'")));

        verify(hostelService).getHostelById(99L);
    }
}
