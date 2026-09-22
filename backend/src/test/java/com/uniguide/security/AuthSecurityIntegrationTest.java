package com.uniguide.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uniguide.dto.LoginRequest;
import com.uniguide.entity.Role;
import com.uniguide.entity.User;
import com.uniguide.repository.DepartmentRepository;
import com.uniguide.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AuthSecurityIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @MockitoBean
    private UserRepository userRepository;

    @MockitoBean
    private com.uniguide.service.DepartmentService departmentService;

    private User teacherUser;
    private User adminUser;
    private String teacherToken;
    private String adminToken;

    @BeforeEach
    void setUp() {
        teacherUser = User.builder()
                .id(101L)
                .email("teacher@university.edu")
                .password(passwordEncoder.encode("TeacherPass123!"))
                .fullName("Prof. Minerva McGonagall")
                .role(Role.TEACHER)
                .build();

        adminUser = User.builder()
                .id(201L)
                .email("admin@university.edu")
                .password(passwordEncoder.encode("AdminPass123!"))
                .fullName("Admin Albus Dumbledore")
                .role(Role.ADMIN)
                .build();

        when(userRepository.findByEmail("teacher@university.edu")).thenReturn(Optional.of(teacherUser));
        when(userRepository.findByEmail("admin@university.edu")).thenReturn(Optional.of(adminUser));

        teacherToken = jwtTokenProvider.generateToken(
                teacherUser.getEmail(),
                teacherUser.getRole().name(),
                teacherUser.getId(),
                teacherUser.getFullName()
        );

        adminToken = jwtTokenProvider.generateToken(
                adminUser.getEmail(),
                adminUser.getRole().name(),
                adminUser.getId(),
                adminUser.getFullName()
        );
    }

    // 1. Valid login
    @Test
    @DisplayName("1. Valid login returns 200 OK with Bearer JWT token and user info")
    void test1_ValidLogin_Success() throws Exception {
        LoginRequest request = LoginRequest.builder()
                .email("teacher@university.edu")
                .password("TeacherPass123!")
                .build();

        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token", notNullValue()))
                .andExpect(jsonPath("$.type", is("Bearer")))
                .andExpect(jsonPath("$.role", is("TEACHER")))
                .andExpect(jsonPath("$.email", is("teacher@university.edu")))
                .andExpect(jsonPath("$.fullName", is("Prof. Minerva McGonagall")))
                .andExpect(jsonPath("$.password").doesNotExist());
    }

    // 2. Invalid password
    @Test
    @DisplayName("2. Invalid password returns 401 Unauthorized")
    void test2_InvalidPassword_Unauthorized() throws Exception {
        LoginRequest request = LoginRequest.builder()
                .email("teacher@university.edu")
                .password("WrongPassword999!")
                .build();

        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.status", is(401)))
                .andExpect(jsonPath("$.error", is("Unauthorized")))
                .andExpect(jsonPath("$.message", is("Invalid email or password")));
    }

    // 3. Unknown user
    @Test
    @DisplayName("3. Unknown user returns 401 Unauthorized")
    void test3_UnknownUser_Unauthorized() throws Exception {
        when(userRepository.findByEmail("nonexistent@university.edu")).thenReturn(Optional.empty());

        LoginRequest request = LoginRequest.builder()
                .email("nonexistent@university.edu")
                .password("AnyPassword123!")
                .build();

        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.status", is(401)))
                .andExpect(jsonPath("$.error", is("Unauthorized")))
                .andExpect(jsonPath("$.message", is("Invalid email or password")));
    }

    // 4. Public API without JWT
    @Test
    @DisplayName("4. Public API without JWT returns 200 OK")
    void test4_PublicApiWithoutJwt_Success() throws Exception {
        when(departmentService.getAllDepartments()).thenReturn(List.of());

        mockMvc.perform(get("/api/departments")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    // 5. Teacher API without JWT
    @Test
    @DisplayName("5. Teacher API without JWT returns 401 Unauthorized")
    void test5_TeacherApiWithoutJwt_Unauthorized() throws Exception {
        mockMvc.perform(get("/api/teacher/dashboard")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.status", is(401)))
                .andExpect(jsonPath("$.error", is("Unauthorized")));
    }

    // 6. Teacher API with TEACHER JWT
    @Test
    @DisplayName("6. Teacher API with TEACHER JWT returns 200 OK")
    void test6_TeacherApiWithTeacherJwt_Success() throws Exception {
        mockMvc.perform(get("/api/teacher/dashboard")
                        .header("Authorization", "Bearer " + teacherToken)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", is("ok")))
                .andExpect(jsonPath("$.message", is("Teacher Dashboard Access Granted")));
    }

    // 7. Admin API with TEACHER JWT
    @Test
    @DisplayName("7. Admin API with TEACHER JWT returns 403 Forbidden")
    void test7_AdminApiWithTeacherJwt_Forbidden() throws Exception {
        mockMvc.perform(get("/api/admin/dashboard")
                        .header("Authorization", "Bearer " + teacherToken)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.status", is(403)))
                .andExpect(jsonPath("$.error", is("Forbidden")))
                .andExpect(jsonPath("$.message", is("Access Denied")));
    }

    // 8. Admin API with ADMIN JWT
    @Test
    @DisplayName("8. Admin API with ADMIN JWT returns 200 OK")
    void test8_AdminApiWithAdminJwt_Success() throws Exception {
        mockMvc.perform(get("/api/admin/dashboard")
                        .header("Authorization", "Bearer " + adminToken)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", is("ok")))
                .andExpect(jsonPath("$.message", is("Admin Dashboard Access Granted")));
    }

    // 9. Invalid JWT
    @Test
    @DisplayName("9. Invalid JWT token returns 401 Unauthorized")
    void test9_InvalidJwt_Unauthorized() throws Exception {
        mockMvc.perform(get("/api/teacher/dashboard")
                        .header("Authorization", "Bearer invalid.malformed.jwt.token")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.status", is(401)))
                .andExpect(jsonPath("$.error", is("Unauthorized")));
    }

    // 10. Expired JWT
    @Test
    @DisplayName("10. Expired JWT token returns 401 Unauthorized")
    void test10_ExpiredJwt_Unauthorized() throws Exception {
        String expiredToken = jwtTokenProvider.generateExpiredToken("teacher@university.edu", "TEACHER");

        mockMvc.perform(get("/api/teacher/dashboard")
                        .header("Authorization", "Bearer " + expiredToken)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.status", is(401)))
                .andExpect(jsonPath("$.error", is("Unauthorized")));
    }
}
