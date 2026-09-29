package com.uniguide.controller;

import com.uniguide.dto.FacilityResponse;
import com.uniguide.exception.GlobalExceptionHandler;
import com.uniguide.exception.ResourceNotFoundException;
import com.uniguide.service.FacilityService;
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
class FacilityControllerTest {

    private MockMvc mockMvc;

    @Mock
    private FacilityService facilityService;

    @InjectMocks
    private FacilityController facilityController;

    private FacilityResponse sampleFacility;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(facilityController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();

        sampleFacility = FacilityResponse.builder()
                .id(1L)
                .name("Central Library")
                .type("LIBRARY")
                .description("Multi-floor academic library with quiet study zones and e-resources.")
                .openingHours("08:00 - 22:00")
                .contactNumber("+1-555-300-4000")
                .locationId(2L)
                .locationName("Knowledge Resource Center")
                .build();
    }

    @Test
    @DisplayName("GET /api/facilities returns all facilities")
    void getFacilities_ReturnsAll() throws Exception {
        when(facilityService.getAllFacilities()).thenReturn(List.of(sampleFacility));

        mockMvc.perform(get("/api/facilities")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("Central Library")))
                .andExpect(jsonPath("$[0].type", is("LIBRARY")));

        verify(facilityService).getAllFacilities();
    }

    @Test
    @DisplayName("GET /api/facilities?type=LIBRARY returns facilities of specified type")
    void getFacilities_TypeFilter() throws Exception {
        when(facilityService.getFacilitiesByType("LIBRARY")).thenReturn(List.of(sampleFacility));

        mockMvc.perform(get("/api/facilities")
                        .param("type", "LIBRARY")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].type", is("LIBRARY")));

        verify(facilityService).getFacilitiesByType("LIBRARY");
    }

    @Test
    @DisplayName("GET /api/facilities?locationId=2 returns facilities in specified location")
    void getFacilities_LocationFilter() throws Exception {
        when(facilityService.getFacilitiesByLocation(2L)).thenReturn(List.of(sampleFacility));

        mockMvc.perform(get("/api/facilities")
                        .param("locationId", "2")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].locationId", is(2)));

        verify(facilityService).getFacilitiesByLocation(2L);
    }

    @Test
    @DisplayName("GET /api/facilities?search=Library returns matched facilities")
    void getFacilities_SearchFilter() throws Exception {
        when(facilityService.searchFacilities("Library")).thenReturn(List.of(sampleFacility));

        mockMvc.perform(get("/api/facilities")
                        .param("search", "Library")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is("Central Library")));

        verify(facilityService).searchFacilities("Library");
    }

    @Test
    @DisplayName("GET /api/facilities/{id} returns facility when found")
    void getFacilityById_Success() throws Exception {
        when(facilityService.getFacilityById(1L)).thenReturn(sampleFacility);

        mockMvc.perform(get("/api/facilities/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Central Library")))
                .andExpect(jsonPath("$.openingHours", is("08:00 - 22:00")));

        verify(facilityService).getFacilityById(1L);
    }

    @Test
    @DisplayName("GET /api/facilities/{id} returns 404 when not found")
    void getFacilityById_NotFound() throws Exception {
        when(facilityService.getFacilityById(99L))
                .thenThrow(new ResourceNotFoundException("Facility", "id", 99L));

        mockMvc.perform(get("/api/facilities/99")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status", is(404)))
                .andExpect(jsonPath("$.error", is("Not Found")))
                .andExpect(jsonPath("$.message", is("Facility not found with id: '99'")));

        verify(facilityService).getFacilityById(99L);
    }
}
