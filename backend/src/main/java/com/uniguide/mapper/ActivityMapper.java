package com.uniguide.mapper;

import com.uniguide.dto.ActivityRequest;
import com.uniguide.dto.ActivityResponse;
import com.uniguide.entity.Activity;
import com.uniguide.entity.CampusLocation;
import org.springframework.stereotype.Component;

/**
 * Mapper for converting between {@link Activity} entity and its DTOs.
 */
@Component
public class ActivityMapper {

    public Activity toEntity(ActivityRequest request, CampusLocation location) {
        if (request == null) {
            return null;
        }
        return Activity.builder()
                .name(request.getName())
                .category(request.getCategory())
                .description(request.getDescription())
                .coordinatorName(request.getCoordinatorName())
                .coordinatorContact(request.getCoordinatorContact())
                .location(location)
                .build();
    }

    public ActivityResponse toResponse(Activity entity) {
        if (entity == null) {
            return null;
        }
        return ActivityResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .category(entity.getCategory())
                .description(entity.getDescription())
                .coordinatorName(entity.getCoordinatorName())
                .coordinatorContact(entity.getCoordinatorContact())
                .locationId(entity.getLocation() != null ? entity.getLocation().getId() : null)
                .locationName(entity.getLocation() != null ? entity.getLocation().getName() : null)
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
