package com.uniguide.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Request payload for creating or updating a {@link com.uniguide.entity.Facility}.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FacilityRequest {

    @NotBlank(message = "Facility name is required")
    private String name;

    private String type;

    private String description;

    private String openingHours;

    private String contactNumber;

    private Long locationId;
}
