package com.uniguide.mapper;

import com.uniguide.dto.EventRequest;
import com.uniguide.dto.EventResponse;
import com.uniguide.entity.CampusLocation;
import com.uniguide.entity.Event;
import org.springframework.stereotype.Component;

/**
 * Mapper for converting between {@link Event} entity and its DTOs.
 */
@Component
public class EventMapper {

    public Event toEntity(EventRequest request, CampusLocation location) {
        if (request == null) {
            return null;
        }
        return Event.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .venue(request.getVenue())
                .organizer(request.getOrganizer())
                .registrationLink(request.getRegistrationLink())
                .location(location)
                .build();
    }

    public EventResponse toResponse(Event entity) {
        if (entity == null) {
            return null;
        }
        return EventResponse.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .startDate(entity.getStartDate())
                .endDate(entity.getEndDate())
                .venue(entity.getVenue())
                .organizer(entity.getOrganizer())
                .registrationLink(entity.getRegistrationLink())
                .locationId(entity.getLocation() != null ? entity.getLocation().getId() : null)
                .locationName(entity.getLocation() != null ? entity.getLocation().getName() : null)
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
