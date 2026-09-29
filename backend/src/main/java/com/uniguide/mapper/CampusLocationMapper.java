package com.uniguide.mapper;

import com.uniguide.dto.CampusLocationRequest;
import com.uniguide.dto.CampusLocationResponse;
import com.uniguide.entity.CampusLocation;
import org.springframework.stereotype.Component;

/**
 * Mapper for converting between {@link CampusLocation} entity and its DTOs.
 */
@Component
public class CampusLocationMapper {

    public CampusLocation toEntity(CampusLocationRequest request) {
        if (request == null) {
            return null;
        }
        return CampusLocation.builder()
                .name(request.getName())
                .code(request.getCode())
                .category(request.getCategory())
                .description(request.getDescription())
                .latitude(request.getLatitude())
                .longitude(request.getLongitude())
                .floorCount(request.getFloorCount())
                .qrCodeKey(request.getQrCodeKey())
                .build();
    }

    public CampusLocationResponse toResponse(CampusLocation entity) {
        if (entity == null) {
            return null;
        }
        return CampusLocationResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .code(entity.getCode())
                .category(entity.getCategory())
                .description(entity.getDescription())
                .latitude(entity.getLatitude())
                .longitude(entity.getLongitude())
                .floorCount(entity.getFloorCount())
                .qrCodeKey(entity.getQrCodeKey())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
