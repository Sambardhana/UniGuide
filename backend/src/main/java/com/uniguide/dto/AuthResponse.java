package com.uniguide.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Response payload returned upon successful authentication.
 * Never exposes passwords or sensitive credentials.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthResponse {

    private String token;

    @Builder.Default
    private String type = "Bearer";

    private String role;
    private Long userId;
    private String email;
    private String fullName;
}
