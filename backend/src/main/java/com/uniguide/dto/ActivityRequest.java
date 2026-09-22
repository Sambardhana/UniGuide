package com.uniguide.dto;

import jakarta.validation.constraints.NotBlank;
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
    private String name;

    private String category;

    private String description;

    private String coordinatorName;

    private String coordinatorContact;

    private Long locationId;
}
