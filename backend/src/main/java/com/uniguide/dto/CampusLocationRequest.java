package com.uniguide.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Request payload for creating or updating a {@link com.uniguide.entity.CampusLocation}.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CampusLocationRequest {

    @NotBlank(message = "Location name is required")
    private String name;

    private String code;

    private String category;

    private String description;

    private Double latitude;

    private Double longitude;

    private Integer floorCount;

    private String qrCodeKey;
}
