package com.uniguide.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
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
    @Size(min = 2, max = 150, message = "Hostel name must be between 2 and 150 characters")
    private String name;

    @Size(max = 50, message = "Hostel type cannot exceed 50 characters")
    private String type;

    @PositiveOrZero(message = "Capacity must be zero or a positive number")
    private Integer capacity;

    @Size(max = 100, message = "Warden name cannot exceed 100 characters")
    private String wardenName;

    @Pattern(regexp = "^$|^\\+?[0-9\\s\\-]{7,20}$", message = "Warden contact must be a valid phone format")
    private String wardenContact;

    @Size(max = 2000, message = "Description cannot exceed 2000 characters")
    private String description;

    @Positive(message = "Location ID must be a positive number")
    private Long locationId;
}
