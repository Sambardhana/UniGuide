package com.uniguide.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Response payload for {@link com.uniguide.entity.Hostel}.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HostelResponse {

    private Long id;
    private String name;
    private String type;
    private Integer capacity;
    private String wardenName;
    private String wardenContact;
    private String description;
    private Long locationId;
    private String locationName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
