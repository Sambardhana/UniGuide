package com.uniguide.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Response payload for {@link com.uniguide.entity.Event}.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventResponse {

    private Long id;
    private String title;
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String venue;
    private String organizer;
    private String registrationLink;
    private Long locationId;
    private String locationName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
