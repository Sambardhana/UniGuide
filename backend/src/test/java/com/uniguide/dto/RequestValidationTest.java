package com.uniguide.dto;

import com.uniguide.entity.Role;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RequestValidationTest {

    private static Validator validator;

    @BeforeAll
    static void setUpValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    @DisplayName("Valid UserRequest should produce no violations")
    void validUserRequestShouldPassValidation() {
        UserRequest request = UserRequest.builder()
                .email("student@university.edu")
                .password("securePassword123")
                .fullName("John Doe")
                .role(Role.STUDENT)
                .phoneNumber("+1-555-123-4567")
                .studentId("STU-2026-001")
                .departmentId(1L)
                .build();

        Set<ConstraintViolation<UserRequest>> violations = validator.validate(request);
        assertTrue(violations.isEmpty(), "Expected no validation violations for valid request");
    }

    @Test
    @DisplayName("Invalid UserRequest should fail on blank email, short password, and negative department ID")
    void invalidUserRequestShouldFailValidation() {
        UserRequest request = UserRequest.builder()
                .email("invalid-email-format")
                .password("123") // too short (< 6)
                .fullName("") // blank
                .role(null) // null
                .departmentId(-5L) // negative
                .build();

        Set<ConstraintViolation<UserRequest>> violations = validator.validate(request);
        assertFalse(violations.isEmpty(), "Expected validation violations for invalid request");
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("email")));
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("password")));
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("fullName")));
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("role")));
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("departmentId")));
    }

    @Test
    @DisplayName("Invalid CourseRequest should fail on invalid credits and blank title")
    void invalidCourseRequestShouldFailValidation() {
        CourseRequest request = CourseRequest.builder()
                .code("CS101")
                .title("") // blank
                .credits(0) // must be >= 1
                .departmentId(null) // required
                .build();

        Set<ConstraintViolation<CourseRequest>> violations = validator.validate(request);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("title")));
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("credits")));
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("departmentId")));
    }
}
