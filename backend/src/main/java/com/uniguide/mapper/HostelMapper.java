package com.uniguide.mapper;

import com.uniguide.dto.HostelRequest;
import com.uniguide.dto.HostelResponse;
import com.uniguide.entity.CampusLocation;
import com.uniguide.entity.Hostel;
import org.springframework.stereotype.Component;

/**
 * Mapper for converting between {@link Hostel} entity and its DTOs.
 */
@Component
public class HostelMapper {

    public Hostel toEntity(HostelRequest request, CampusLocation location) {
        if (request == null) {
            return null;
        }
        return Hostel.builder()
                .name(request.getName())
                .type(request.getType())
                .capacity(request.getCapacity())
                .wardenName(request.getWardenName())
                .wardenContact(request.getWardenContact())
                .description(request.getDescription())
                .location(location)
                .build();
    }

    public HostelResponse toResponse(Hostel entity) {
        if (entity == null) {
            return null;
        }
        return HostelResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .type(entity.getType())
                .capacity(entity.getCapacity())
                .wardenName(entity.getWardenName())
                .wardenContact(entity.getWardenContact())
                .description(entity.getDescription())
                .locationId(entity.getLocation() != null ? entity.getLocation().getId() : null)
                .locationName(entity.getLocation() != null ? entity.getLocation().getName() : null)
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
