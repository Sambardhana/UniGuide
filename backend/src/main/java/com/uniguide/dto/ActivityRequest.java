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
 * Request payload for creating or updating an {@link com.uniguide.entity.Activity}.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActivityRequest {

    @NotBlank(message = "Activity name is required")
    @Size(min = 2, max = 150, message = "Activity name must be between 2 and 150 characters")
    private String name;

    @Size(max = 50, message = "Category cannot exceed 50 characters")
    private String category;

    @Size(max = 2000, message = "Description cannot exceed 2000 characters")
    private String description;

    @Size(max = 100, message = "Coordinator name cannot exceed 100 characters")
    private String coordinatorName;

    @Pattern(regexp = "^$|^\\+?[0-9\\s\\-]{7,20}$", message = "Coordinator contact must be a valid phone format")
    private String coordinatorContact;

    @Positive(message = "Location ID must be a positive number")
    private Long locationId;
}
