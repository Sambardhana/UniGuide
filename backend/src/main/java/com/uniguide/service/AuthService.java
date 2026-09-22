package com.uniguide.service;

import com.uniguide.dto.AuthResponse;
import com.uniguide.dto.LoginRequest;
import com.uniguide.entity.User;
import com.uniguide.repository.UserRepository;
import com.uniguide.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service managing user authentication, password verification using BCrypt,
 * and JWT token issuance.
 */
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    /**
     * Authenticates a user with email and password credentials.
     *
     * @param request the login request payload containing email and raw password
     * @return AuthResponse containing signed JWT token, role, and public profile data
     * @throws BadCredentialsException if credentials are invalid or user does not exist
     */
    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new BadCredentialsException("Invalid email or password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Invalid email or password");
        }

        String role = user.getRole() != null ? user.getRole().name() : "STUDENT";
        String token = jwtTokenProvider.generateToken(
                user.getEmail(),
                role,
                user.getId(),
                user.getFullName()
        );

        return AuthResponse.builder()
                .token(token)
                .type("Bearer")
                .role(role)
                .userId(user.getId())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .build();
    }
}
