package com.uniguide.controller;

import com.uniguide.dto.EventResponse;
import com.uniguide.exception.GlobalExceptionHandler;
import com.uniguide.exception.ResourceNotFoundException;
import com.uniguide.service.EventService;
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

import java.time.LocalDateTime;
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
class EventControllerTest {

    private MockMvc mockMvc;

    @Mock
    private EventService eventService;

    @InjectMocks
    private EventController eventController;

    private EventResponse sampleEvent;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(eventController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();

        sampleEvent = EventResponse.builder()
                .id(1L)
                .title("Annual Hackathon 2026")
                .description("36-hour coding competition open to all departments.")
                .startDate(LocalDateTime.of(2026, 11, 10, 10, 0))
                .endDate(LocalDateTime.of(2026, 11, 11, 22, 0))
                .venue("Auditorium Hall A")
                .organizer("Coding Society")
                .registrationLink("https://uniguide.edu/hackathon2026")
                .locationId(4L)
                .locationName("Main Auditorium")
                .build();
    }

    @Test
    @DisplayName("GET /api/events returns all events")
    void getEvents_ReturnsAll() throws Exception {
        when(eventService.getAllEvents()).thenReturn(List.of(sampleEvent));

        mockMvc.perform(get("/api/events")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].title", is("Annual Hackathon 2026")))
                .andExpect(jsonPath("$[0].organizer", is("Coding Society")));

        verify(eventService).getAllEvents();
    }

    @Test
    @DisplayName("GET /api/events?locationId=4 returns events in location")
    void getEvents_LocationFilter() throws Exception {
        when(eventService.getEventsByLocation(4L)).thenReturn(List.of(sampleEvent));

        mockMvc.perform(get("/api/events")
                        .param("locationId", "4")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].locationId", is(4)));

        verify(eventService).getEventsByLocation(4L);
    }

    @Test
    @DisplayName("GET /api/events?upcoming=true returns upcoming events")
    void getEvents_UpcomingFilter() throws Exception {
        when(eventService.getUpcomingEvents()).thenReturn(List.of(sampleEvent));

        mockMvc.perform(get("/api/events")
                        .param("upcoming", "true")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].title", is("Annual Hackathon 2026")));

        verify(eventService).getUpcomingEvents();
    }

    @Test
    @DisplayName("GET /api/events with date range filter")
    void getEvents_DateRangeFilter() throws Exception {
        LocalDateTime start = LocalDateTime.of(2026, 11, 1, 0, 0);
        LocalDateTime end = LocalDateTime.of(2026, 11, 30, 23, 59);

        when(eventService.getEventsBetween(start, end)).thenReturn(List.of(sampleEvent));

        mockMvc.perform(get("/api/events")
                        .param("start", "2026-11-01T00:00:00")
                        .param("end", "2026-11-30T23:59:00")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].title", is("Annual Hackathon 2026")));

        verify(eventService).getEventsBetween(start, end);
    }

    @Test
    @DisplayName("GET /api/events/{id} returns event when found")
    void getEventById_Success() throws Exception {
        when(eventService.getEventById(1L)).thenReturn(sampleEvent);

        mockMvc.perform(get("/api/events/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("Annual Hackathon 2026")))
                .andExpect(jsonPath("$.venue", is("Auditorium Hall A")));

        verify(eventService).getEventById(1L);
    }

    @Test
    @DisplayName("GET /api/events/{id} returns 404 when not found")
    void getEventById_NotFound() throws Exception {
        when(eventService.getEventById(99L))
                .thenThrow(new ResourceNotFoundException("Event", "id", 99L));

        mockMvc.perform(get("/api/events/99")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status", is(404)))
                .andExpect(jsonPath("$.error", is("Not Found")))
                .andExpect(jsonPath("$.message", is("Event not found with id: '99'")));

        verify(eventService).getEventById(99L);
    }

    @Test
    @DisplayName("GET /api/events/upcoming returns upcoming events")
    void getUpcomingEvents_Success() throws Exception {
        when(eventService.getUpcomingEvents()).thenReturn(List.of(sampleEvent));

        mockMvc.perform(get("/api/events/upcoming")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].title", is("Annual Hackathon 2026")));

        verify(eventService).getUpcomingEvents();
    }
}
