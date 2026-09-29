package com.uniguide.controller;

import com.uniguide.dto.ActivityResponse;
import com.uniguide.exception.GlobalExceptionHandler;
import com.uniguide.exception.ResourceNotFoundException;
import com.uniguide.service.ActivityService;
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
class ActivityControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ActivityService activityService;

    @InjectMocks
    private ActivityController activityController;

    private ActivityResponse sampleActivity;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(activityController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();

        sampleActivity = ActivityResponse.builder()
                .id(1L)
                .name("Robotics Club")
                .category("TECHNICAL")
                .description("Student club designing autonomous robots and drones.")
                .coordinatorName("Dr. Sarah Connor")
                .coordinatorContact("robotics@university.edu")
                .locationId(3L)
                .locationName("Innovation Lab")
                .build();
    }

    @Test
    @DisplayName("GET /api/activities returns all activities")
    void getActivities_ReturnsAll() throws Exception {
        when(activityService.getAllActivities()).thenReturn(List.of(sampleActivity));

        mockMvc.perform(get("/api/activities")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("Robotics Club")))
                .andExpect(jsonPath("$[0].category", is("TECHNICAL")));

        verify(activityService).getAllActivities();
    }

    @Test
    @DisplayName("GET /api/activities?category=TECHNICAL returns filtered activities")
    void getActivities_CategoryFilter() throws Exception {
        when(activityService.getActivitiesByCategory("TECHNICAL")).thenReturn(List.of(sampleActivity));

        mockMvc.perform(get("/api/activities")
                        .param("category", "TECHNICAL")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].category", is("TECHNICAL")));

        verify(activityService).getActivitiesByCategory("TECHNICAL");
    }

    @Test
    @DisplayName("GET /api/activities?locationId=3 returns activities in location")
    void getActivities_LocationFilter() throws Exception {
        when(activityService.getActivitiesByLocation(3L)).thenReturn(List.of(sampleActivity));

        mockMvc.perform(get("/api/activities")
                        .param("locationId", "3")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].locationId", is(3)));

        verify(activityService).getActivitiesByLocation(3L);
    }

    @Test
    @DisplayName("GET /api/activities?search=Robotics returns matched activities")
    void getActivities_SearchFilter() throws Exception {
        when(activityService.searchActivities("Robotics")).thenReturn(List.of(sampleActivity));

        mockMvc.perform(get("/api/activities")
                        .param("search", "Robotics")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is("Robotics Club")));

        verify(activityService).searchActivities("Robotics");
    }

    @Test
    @DisplayName("GET /api/activities/{id} returns activity when found")
    void getActivityById_Success() throws Exception {
        when(activityService.getActivityById(1L)).thenReturn(sampleActivity);

        mockMvc.perform(get("/api/activities/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Robotics Club")))
                .andExpect(jsonPath("$.coordinatorName", is("Dr. Sarah Connor")));

        verify(activityService).getActivityById(1L);
    }

    @Test
    @DisplayName("GET /api/activities/{id} returns 404 when not found")
    void getActivityById_NotFound() throws Exception {
        when(activityService.getActivityById(99L))
                .thenThrow(new ResourceNotFoundException("Activity", "id", 99L));

        mockMvc.perform(get("/api/activities/99")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status", is(404)))
                .andExpect(jsonPath("$.error", is("Not Found")))
                .andExpect(jsonPath("$.message", is("Activity not found with id: '99'")));

        verify(activityService).getActivityById(99L);
    }

    @Test
    @DisplayName("GET /api/activities/category/{category} returns activities for path category")
    void getActivitiesByCategoryPath_Success() throws Exception {
        when(activityService.getActivitiesByCategory("TECHNICAL")).thenReturn(List.of(sampleActivity));

        mockMvc.perform(get("/api/activities/category/TECHNICAL")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].category", is("TECHNICAL")));

        verify(activityService).getActivitiesByCategory("TECHNICAL");
    }
}
