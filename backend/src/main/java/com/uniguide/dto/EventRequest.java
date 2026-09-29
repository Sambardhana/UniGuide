package com.uniguide.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Request payload for creating or updating an {@link com.uniguide.entity.Event}.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventRequest {

    @NotBlank(message = "Event title is required")
    @Size(min = 2, max = 200, message = "Event title must be between 2 and 200 characters")
    private String title;

    @Size(max = 4000, message = "Description cannot exceed 4000 characters")
    private String description;

    @NotNull(message = "Start date is required")
    private LocalDateTime startDate;

    private LocalDateTime endDate;

    @Size(max = 150, message = "Venue cannot exceed 150 characters")
    private String venue;

    @Size(max = 100, message = "Organizer cannot exceed 100 characters")
    private String organizer;

    @Size(max = 500, message = "Registration link cannot exceed 500 characters")
    private String registrationLink;

    @Positive(message = "Location ID must be a positive number")
    private Long locationId;
}
