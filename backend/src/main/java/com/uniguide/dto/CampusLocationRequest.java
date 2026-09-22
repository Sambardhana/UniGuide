package com.uniguide.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
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
    @Size(min = 2, max = 150, message = "Location name must be between 2 and 150 characters")
    private String name;

    @Size(max = 30, message = "Code cannot exceed 30 characters")
    private String code;

    @Size(max = 50, message = "Category cannot exceed 50 characters")
    private String category;

    @Size(max = 2000, message = "Description cannot exceed 2000 characters")
    private String description;

    private Double latitude;

    private Double longitude;

    @Positive(message = "Floor count must be a positive number")
    private Integer floorCount;

    @Size(max = 100, message = "QR code key cannot exceed 100 characters")
    private String qrCodeKey;
}
