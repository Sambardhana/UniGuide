package com.uniguide.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Response payload for {@link com.uniguide.entity.CampusLocation}.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CampusLocationResponse {

    private Long id;
    private String name;
    private String code;
    private String category;
    private String description;
    private Double latitude;
    private Double longitude;
    private Integer floorCount;
    private String qrCodeKey;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
