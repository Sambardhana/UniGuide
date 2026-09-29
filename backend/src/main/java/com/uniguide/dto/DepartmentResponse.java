package com.uniguide.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Response payload for {@link com.uniguide.entity.Department}.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DepartmentResponse {

    private Long id;
    private String name;
    private String code;
    private String description;
    private String contactEmail;
    private String contactPhone;
    private Long locationId;
    private String locationName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
