package com.uniguide.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
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
    @Size(min = 2, max = 150, message = "Facility name must be between 2 and 150 characters")
    private String name;

    @Size(max = 50, message = "Facility type cannot exceed 50 characters")
    private String type;

    @Size(max = 2000, message = "Description cannot exceed 2000 characters")
    private String description;

    @Size(max = 100, message = "Opening hours cannot exceed 100 characters")
    private String openingHours;

    @Pattern(regexp = "^$|^\\+?[0-9\\s\\-]{7,20}$", message = "Contact number must be a valid phone format")
    private String contactNumber;

    @Positive(message = "Location ID must be a positive number")
    private Long locationId;
}
