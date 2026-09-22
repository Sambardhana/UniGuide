package com.uniguide.controller;

import com.uniguide.dto.EventResponse;
import com.uniguide.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

/**
 * REST Controller exposing student-facing read endpoints for campus events, workshops, hackathons, and festivals.
 */
@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    /**
     * Retrieves campus events with optional filtering by location, date range, or upcoming status.
     *
     * @param locationId optional campus location filter
     * @param upcoming   optional flag to return only upcoming events
     * @param start      optional start date-time boundary (ISO-8601)
     * @param end        optional end date-time boundary (ISO-8601)
     * @return list of event response DTOs
     */
    @GetMapping
    public ResponseEntity<List<EventResponse>> getEvents(
            @RequestParam(required = false) Long locationId,
            @RequestParam(required = false) Boolean upcoming,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {

        if (start != null && end != null) {
            return ResponseEntity.ok(eventService.getEventsBetween(start, end));
        }
        if (locationId != null) {
            return ResponseEntity.ok(eventService.getEventsByLocation(locationId));
        }
        if (Boolean.TRUE.equals(upcoming)) {
            return ResponseEntity.ok(eventService.getUpcomingEvents());
        }
        return ResponseEntity.ok(eventService.getAllEvents());
    }

    /**
     * Retrieves a single event by its identifier.
     *
     * @param id the primary key identifier of the event
     * @return event response DTO
     */
    @GetMapping("/{id}")
    public ResponseEntity<EventResponse> getEventById(@PathVariable Long id) {
        return ResponseEntity.ok(eventService.getEventById(id));
    }

    /**
     * Convenience endpoint to retrieve upcoming campus events.
     *
     * @return list of upcoming event response DTOs
     */
    @GetMapping("/upcoming")
    public ResponseEntity<List<EventResponse>> getUpcomingEvents() {
        return ResponseEntity.ok(eventService.getUpcomingEvents());
    }
}
