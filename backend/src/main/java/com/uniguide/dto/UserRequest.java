package com.uniguide.dto;

import com.uniguide.entity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Request payload for creating or updating a {@link com.uniguide.entity.User}.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequest {

    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    @Size(max = 100, message = "Email cannot exceed 100 characters")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 6, max = 100, message = "Password must be between 6 and 100 characters")
    private String password;

    @NotBlank(message = "Full name is required")
    @Size(min = 2, max = 100, message = "Full name must be between 2 and 100 characters")
    private String fullName;

    @NotNull(message = "Role is required")
    private Role role;

    @Pattern(regexp = "^$|^\\+?[0-9\\s\\-]{7,20}$", message = "Phone number must be a valid phone format")
    private String phoneNumber;

    @Size(max = 30, message = "Student ID cannot exceed 30 characters")
    private String studentId;

    @Positive(message = "Department ID must be a positive number")
    private Long departmentId;
}
