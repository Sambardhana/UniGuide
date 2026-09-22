package com.uniguide.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class GlobalExceptionHandlerTest {

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @RestController
    static class ExceptionTestController {

        @GetMapping("/test/not-found")
        public void throwNotFound() {
            throw new ResourceNotFoundException("Department", "id", 99L);
        }

        @GetMapping("/test/bad-request")
        public void throwBadRequest() {
            throw new BadRequestException("Invalid query parameter");
        }

        @GetMapping("/test/illegal-argument")
        public void throwIllegalArgument() {
            throw new IllegalArgumentException("Negative values are not permitted");
        }

        @GetMapping("/test/unauthorized")
        public void throwUnauthorized() {
            throw new UnauthorizedException("Session has expired");
        }

        @GetMapping("/test/bad-credentials")
        public void throwBadCredentials() {
            throw new BadCredentialsException("Invalid email or password");
        }

        @GetMapping("/test/auth-exception")
        public void throwAuthException() {
            throw new InsufficientAuthenticationException("Authentication credentials missing");
        }

        @GetMapping("/test/forbidden")
        public void throwForbidden() {
            throw new ForbiddenException("Requires elevated privileges");
        }

        @GetMapping("/test/access-denied")
        public void throwAccessDenied() {
            throw new AccessDeniedException("Access is denied: insufficient role privileges");
        }

        @GetMapping("/test/conflict")
        public void throwConflict() {
            throw new DuplicateResourceException("Department", "code", "CSE");
        }

        @GetMapping("/test/unexpected-error")
        public void throwUnexpected() {
            throw new RuntimeException("Null pointer in private database driver!");
        }

        @PostMapping("/test/validation")
        public void testValidation(@Valid @RequestBody DummyValidationRequest request) {
        }
    }

    @Data
    static class DummyValidationRequest {
        @NotBlank(message = "Field cannot be blank")
        private String requiredField;
    }

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new ExceptionTestController())
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
        objectMapper = new ObjectMapper();
    }

    @Test
    @DisplayName("404 ResourceNotFoundException returns standard JSON with path")
    void testResourceNotFoundException() throws Exception {
        mockMvc.perform(get("/test/not-found"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status", is(404)))
                .andExpect(jsonPath("$.error", is("Not Found")))
                .andExpect(jsonPath("$.message", is("Department not found with id: '99'")))
                .andExpect(jsonPath("$.path", is("/test/not-found")))
                .andExpect(jsonPath("$.timestamp", notNullValue()));
    }

    @Test
    @DisplayName("400 BadRequestException returns standard JSON with path")
    void testBadRequestException() throws Exception {
        mockMvc.perform(get("/test/bad-request"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status", is(400)))
                .andExpect(jsonPath("$.error", is("Bad Request")))
                .andExpect(jsonPath("$.message", is("Invalid query parameter")))
                .andExpect(jsonPath("$.path", is("/test/bad-request")));
    }

    @Test
    @DisplayName("400 IllegalArgumentException returns standard JSON with path")
    void testIllegalArgumentException() throws Exception {
        mockMvc.perform(get("/test/illegal-argument"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status", is(400)))
                .andExpect(jsonPath("$.error", is("Bad Request")))
                .andExpect(jsonPath("$.message", is("Negative values are not permitted")))
                .andExpect(jsonPath("$.path", is("/test/illegal-argument")));
    }

    @Test
    @DisplayName("400 MethodArgumentNotValidException returns validation errors with path")
    void testValidationException() throws Exception {
        DummyValidationRequest request = new DummyValidationRequest();
        request.setRequiredField("");

        mockMvc.perform(post("/test/validation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status", is(400)))
                .andExpect(jsonPath("$.error", is("Validation Failed")))
                .andExpect(jsonPath("$.path", is("/test/validation")))
                .andExpect(jsonPath("$.errors.requiredField", is("Field cannot be blank")));
    }

    @Test
    @DisplayName("401 UnauthorizedException returns standard JSON with path")
    void testUnauthorizedException() throws Exception {
        mockMvc.perform(get("/test/unauthorized"))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.status", is(401)))
                .andExpect(jsonPath("$.error", is("Unauthorized")))
                .andExpect(jsonPath("$.message", is("Session has expired")))
                .andExpect(jsonPath("$.path", is("/test/unauthorized")));
    }

    @Test
    @DisplayName("401 BadCredentialsException returns standard JSON with path")
    void testBadCredentialsException() throws Exception {
        mockMvc.perform(get("/test/bad-credentials"))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.status", is(401)))
                .andExpect(jsonPath("$.error", is("Unauthorized")))
                .andExpect(jsonPath("$.message", is("Invalid email or password")))
                .andExpect(jsonPath("$.path", is("/test/bad-credentials")));
    }

    @Test
    @DisplayName("401 AuthenticationException returns standard JSON with path")
    void testAuthenticationException() throws Exception {
        mockMvc.perform(get("/test/auth-exception"))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.status", is(401)))
                .andExpect(jsonPath("$.error", is("Unauthorized")))
                .andExpect(jsonPath("$.path", is("/test/auth-exception")));
    }

    @Test
    @DisplayName("403 ForbiddenException returns standard JSON with path")
    void testForbiddenException() throws Exception {
        mockMvc.perform(get("/test/forbidden"))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.status", is(403)))
                .andExpect(jsonPath("$.error", is("Forbidden")))
                .andExpect(jsonPath("$.message", is("Requires elevated privileges")))
                .andExpect(jsonPath("$.path", is("/test/forbidden")));
    }

    @Test
    @DisplayName("403 AccessDeniedException returns standard JSON with path")
    void testAccessDeniedException() throws Exception {
        mockMvc.perform(get("/test/access-denied"))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.status", is(403)))
                .andExpect(jsonPath("$.error", is("Forbidden")))
                .andExpect(jsonPath("$.path", is("/test/access-denied")));
    }

    @Test
    @DisplayName("409 DuplicateResourceException returns standard JSON with path")
    void testDuplicateResourceException() throws Exception {
        mockMvc.perform(get("/test/conflict"))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.status", is(409)))
                .andExpect(jsonPath("$.error", is("Conflict")))
                .andExpect(jsonPath("$.message", is("Department already exists with code: 'CSE'")))
                .andExpect(jsonPath("$.path", is("/test/conflict")));
    }

    @Test
    @DisplayName("500 Unexpected server error returns sanitized JSON without stack trace")
    void testUnexpectedServerError_Sanitized() throws Exception {
        mockMvc.perform(get("/test/unexpected-error"))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.status", is(500)))
                .andExpect(jsonPath("$.error", is("Internal Server Error")))
                .andExpect(jsonPath("$.message", is("An unexpected error occurred. Please try again later.")))
                .andExpect(jsonPath("$.path", is("/test/unexpected-error")))
                .andExpect(jsonPath("$.timestamp", notNullValue()));
    }
}
