package com.uniguide.mapper;

import com.uniguide.dto.FacilityRequest;
import com.uniguide.dto.FacilityResponse;
import com.uniguide.entity.CampusLocation;
import com.uniguide.entity.Facility;
import org.springframework.stereotype.Component;

/**
 * Mapper for converting between {@link Facility} entity and its DTOs.
 */
@Component
public class FacilityMapper {

    public Facility toEntity(FacilityRequest request, CampusLocation location) {
        if (request == null) {
            return null;
        }
        return Facility.builder()
                .name(request.getName())
                .type(request.getType())
                .description(request.getDescription())
                .openingHours(request.getOpeningHours())
                .contactNumber(request.getContactNumber())
                .location(location)
                .build();
    }

    public FacilityResponse toResponse(Facility entity) {
        if (entity == null) {
            return null;
        }
        return FacilityResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .type(entity.getType())
                .description(entity.getDescription())
                .openingHours(entity.getOpeningHours())
                .contactNumber(entity.getContactNumber())
                .locationId(entity.getLocation() != null ? entity.getLocation().getId() : null)
                .locationName(entity.getLocation() != null ? entity.getLocation().getName() : null)
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
