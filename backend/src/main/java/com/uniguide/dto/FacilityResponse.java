package com.uniguide.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Response payload for {@link com.uniguide.entity.Facility}.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FacilityResponse {

    private Long id;
    private String name;
    private String type;
    private String description;
    private String openingHours;
    private String contactNumber;
    private Long locationId;
    private String locationName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
