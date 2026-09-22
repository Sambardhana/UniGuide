package com.uniguide.mapper;

import com.uniguide.dto.DepartmentRequest;
import com.uniguide.dto.DepartmentResponse;
import com.uniguide.entity.CampusLocation;
import com.uniguide.entity.Department;
import org.springframework.stereotype.Component;

/**
 * Mapper for converting between {@link Department} entity and its DTOs.
 */
@Component
public class DepartmentMapper {

    public Department toEntity(DepartmentRequest request, CampusLocation location) {
        if (request == null) {
            return null;
        }
        return Department.builder()
                .name(request.getName())
                .code(request.getCode())
                .description(request.getDescription())
                .contactEmail(request.getContactEmail())
                .contactPhone(request.getContactPhone())
                .location(location)
                .build();
    }

    public DepartmentResponse toResponse(Department entity) {
        if (entity == null) {
            return null;
        }
        return DepartmentResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .code(entity.getCode())
                .description(entity.getDescription())
                .contactEmail(entity.getContactEmail())
                .contactPhone(entity.getContactPhone())
                .locationId(entity.getLocation() != null ? entity.getLocation().getId() : null)
                .locationName(entity.getLocation() != null ? entity.getLocation().getName() : null)
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
