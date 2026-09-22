package com.uniguide.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Request payload for creating or updating a {@link com.uniguide.entity.Department}.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DepartmentRequest {

    @NotBlank(message = "Department name is required")
    @Size(min = 2, max = 150, message = "Department name must be between 2 and 150 characters")
    private String name;

    @NotBlank(message = "Department code is required")
    @Size(min = 2, max = 20, message = "Department code must be between 2 and 20 characters")
    @Pattern(regexp = "^[A-Za-z0-9_-]+$", message = "Department code must contain only alphanumeric characters, underscores, or hyphens")
    private String code;

    @Size(max = 2000, message = "Description cannot exceed 2000 characters")
    private String description;

    @Email(message = "Contact email must be valid")
    @Size(max = 100, message = "Contact email cannot exceed 100 characters")
    private String contactEmail;

    @Pattern(regexp = "^$|^\\+?[0-9\\s\\-]{7,20}$", message = "Contact phone must be a valid phone format")
    private String contactPhone;

    @Positive(message = "Location ID must be a positive number")
    private Long locationId;
}
