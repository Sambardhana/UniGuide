package com.uniguide.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Request payload for creating or updating a {@link com.uniguide.entity.Contact}.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContactRequest {

    @NotBlank(message = "Contact name is required")
    private String name;

    private String designation;

    private String category;

    @NotBlank(message = "Phone number is required")
    private String phoneNumber;

    @Email(message = "Email must be valid")
    private String email;

    private String officeLocation;

    private Long departmentId;
}
