package com.uniguide.controller;

import com.uniguide.dto.CampusLocationResponse;
import com.uniguide.exception.GlobalExceptionHandler;
import com.uniguide.exception.ResourceNotFoundException;
import com.uniguide.service.CampusLocationService;
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
class CampusLocationControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CampusLocationService campusLocationService;

    @InjectMocks
    private CampusLocationController campusLocationController;

    private CampusLocationResponse sampleLocation;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(campusLocationController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();

        sampleLocation = CampusLocationResponse.builder()
                .id(1L)
                .name("Administrative Block")
                .code("ADM-BLK")
                .category("ADMINISTRATIVE")
                .description("Central university administration and registrar office.")
                .latitude(12.9716)
                .longitude(77.5946)
                .floorCount(4)
                .qrCodeKey("QR-ADM-01")
                .build();
    }

    @Test
    @DisplayName("GET /api/campus-locations returns all campus locations")
    void getCampusLocations_ReturnsAll() throws Exception {
        when(campusLocationService.getAllLocations()).thenReturn(List.of(sampleLocation));

        mockMvc.perform(get("/api/campus-locations")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("Administrative Block")))
                .andExpect(jsonPath("$[0].code", is("ADM-BLK")));

        verify(campusLocationService).getAllLocations();
    }

    @Test
    @DisplayName("GET /api/campus-locations?category=ADMINISTRATIVE returns filtered locations")
    void getCampusLocations_CategoryFilter() throws Exception {
        when(campusLocationService.getLocationsByCategory("ADMINISTRATIVE")).thenReturn(List.of(sampleLocation));

        mockMvc.perform(get("/api/campus-locations")
                        .param("category", "ADMINISTRATIVE")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].category", is("ADMINISTRATIVE")));

        verify(campusLocationService).getLocationsByCategory("ADMINISTRATIVE");
    }

    @Test
    @DisplayName("GET /api/campus-locations?search=Admin returns matched locations")
    void getCampusLocations_SearchFilter() throws Exception {
        when(campusLocationService.searchLocations("Admin")).thenReturn(List.of(sampleLocation));

        mockMvc.perform(get("/api/campus-locations")
                        .param("search", "Admin")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is("Administrative Block")));

        verify(campusLocationService).searchLocations("Admin");
    }

    @Test
    @DisplayName("GET /api/campus-locations?code=ADM-BLK returns location by code")
    void getCampusLocations_CodeFilter() throws Exception {
        when(campusLocationService.getLocationByCode("ADM-BLK")).thenReturn(sampleLocation);

        mockMvc.perform(get("/api/campus-locations")
                        .param("code", "ADM-BLK")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].code", is("ADM-BLK")));

        verify(campusLocationService).getLocationByCode("ADM-BLK");
    }

    @Test
    @DisplayName("GET /api/campus-locations?qrCodeKey=QR-ADM-01 returns location by QR key")
    void getCampusLocations_QrCodeKeyFilter() throws Exception {
        when(campusLocationService.getLocationByQrCodeKey("QR-ADM-01")).thenReturn(sampleLocation);

        mockMvc.perform(get("/api/campus-locations")
                        .param("qrCodeKey", "QR-ADM-01")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].qrCodeKey", is("QR-ADM-01")));

        verify(campusLocationService).getLocationByQrCodeKey("QR-ADM-01");
    }

    @Test
    @DisplayName("GET /api/campus-locations/{id} returns location when found")
    void getCampusLocationById_Success() throws Exception {
        when(campusLocationService.getLocationById(1L)).thenReturn(sampleLocation);

        mockMvc.perform(get("/api/campus-locations/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Administrative Block")))
                .andExpect(jsonPath("$.latitude", is(12.9716)))
                .andExpect(jsonPath("$.longitude", is(77.5946)));

        verify(campusLocationService).getLocationById(1L);
    }

    @Test
    @DisplayName("GET /api/campus-locations/{id} returns 404 when not found")
    void getCampusLocationById_NotFound() throws Exception {
        when(campusLocationService.getLocationById(99L))
                .thenThrow(new ResourceNotFoundException("CampusLocation", "id", 99L));

        mockMvc.perform(get("/api/campus-locations/99")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status", is(404)))
                .andExpect(jsonPath("$.error", is("Not Found")))
                .andExpect(jsonPath("$.message", is("CampusLocation not found with id: '99'")));

        verify(campusLocationService).getLocationById(99L);
    }

    @Test
    @DisplayName("GET /api/campus-locations/qr/{qrCodeKey} returns location")
    void getCampusLocationByQrCode_Success() throws Exception {
        when(campusLocationService.getLocationByQrCodeKey("QR-ADM-01")).thenReturn(sampleLocation);

        mockMvc.perform(get("/api/campus-locations/qr/QR-ADM-01")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.qrCodeKey", is("QR-ADM-01")))
                .andExpect(jsonPath("$.name", is("Administrative Block")));

        verify(campusLocationService).getLocationByQrCodeKey("QR-ADM-01");
    }
}
