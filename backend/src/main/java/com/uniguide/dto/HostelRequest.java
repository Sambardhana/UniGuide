package com.uniguide.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Request payload for creating or updating a {@link com.uniguide.entity.Hostel}.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HostelRequest {

    @NotBlank(message = "Hostel name is required")
    private String name;

    private String type;

    @Min(value = 0, message = "Capacity must be non-negative")
    private Integer capacity;

    private String wardenName;

    private String wardenContact;

    private String description;

    private Long locationId;
}
