package com.uniguide.service;

import com.uniguide.dto.EventRequest;
import com.uniguide.dto.EventResponse;
import com.uniguide.entity.CampusLocation;
import com.uniguide.entity.Event;
import com.uniguide.exception.ResourceNotFoundException;
import com.uniguide.mapper.EventMapper;
import com.uniguide.repository.CampusLocationRepository;
import com.uniguide.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Service managing campus orientations, workshops, hackathons, and cultural fests.
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EventService {

    private final EventRepository eventRepository;
    private final CampusLocationRepository campusLocationRepository;
    private final EventMapper eventMapper;

    public List<EventResponse> getAllEvents() {
        return eventRepository.findAllByOrderByStartDateAsc().stream()
                .map(eventMapper::toResponse)
                .toList();
    }

    public List<EventResponse> getUpcomingEvents() {
        return eventRepository.findByStartDateAfterOrderByStartDateAsc(LocalDateTime.now()).stream()
                .map(eventMapper::toResponse)
                .toList();
    }

    public EventResponse getEventById(Long id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event", "id", id));
        return eventMapper.toResponse(event);
    }

    public List<EventResponse> getEventsByLocation(Long locationId) {
        return eventRepository.findByLocationId(locationId).stream()
                .map(eventMapper::toResponse)
                .toList();
    }

    public List<EventResponse> getEventsBetween(LocalDateTime start, LocalDateTime end) {
        return eventRepository.findByStartDateBetweenOrderByStartDateAsc(start, end).stream()
                .map(eventMapper::toResponse)
                .toList();
    }

    @Transactional
    public EventResponse createEvent(EventRequest request) {
        CampusLocation location = null;
        if (request.getLocationId() != null) {
            location = campusLocationRepository.findById(request.getLocationId())
                    .orElseThrow(() -> new ResourceNotFoundException("CampusLocation", "id", request.getLocationId()));
        }

        Event event = eventMapper.toEntity(request, location);
        Event savedEvent = eventRepository.save(event);
        return eventMapper.toResponse(savedEvent);
    }

    @Transactional
    public EventResponse updateEvent(Long id, EventRequest request) {
        Event existing = eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event", "id", id));

        CampusLocation location = null;
        if (request.getLocationId() != null) {
            location = campusLocationRepository.findById(request.getLocationId())
                    .orElseThrow(() -> new ResourceNotFoundException("CampusLocation", "id", request.getLocationId()));
        }

        existing.setTitle(request.getTitle());
        existing.setDescription(request.getDescription());
        existing.setStartDate(request.getStartDate());
        existing.setEndDate(request.getEndDate());
        existing.setVenue(request.getVenue());
        existing.setOrganizer(request.getOrganizer());
        existing.setRegistrationLink(request.getRegistrationLink());
        existing.setLocation(location);

        Event updated = eventRepository.save(existing);
        return eventMapper.toResponse(updated);
    }

    @Transactional
    public void deleteEvent(Long id) {
        if (!eventRepository.existsById(id)) {
            throw new ResourceNotFoundException("Event", "id", id);
        }
        eventRepository.deleteById(id);
    }
}
