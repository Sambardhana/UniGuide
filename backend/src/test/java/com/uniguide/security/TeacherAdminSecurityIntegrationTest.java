package com.uniguide.security;

import com.uniguide.dto.DepartmentResponse;
import com.uniguide.dto.UserResponse;
import com.uniguide.entity.Role;
import com.uniguide.entity.User;
import com.uniguide.repository.UserRepository;
import com.uniguide.service.DepartmentService;
import com.uniguide.service.UserService;
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
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TeacherAdminSecurityIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @MockitoBean
    private UserRepository userRepository;

    @MockitoBean
    private UserService userService;

    @MockitoBean
    private DepartmentService departmentService;

    private String teacherToken;
    private String adminToken;
    private String studentToken;

    @BeforeEach
    void setUp() {
        User teacherUser = User.builder()
                .id(101L)
                .email("teacher@university.edu")
                .password(passwordEncoder.encode("Pass123!"))
                .fullName("Prof. Minerva McGonagall")
                .role(Role.TEACHER)
                .build();

        User adminUser = User.builder()
                .id(201L)
                .email("admin@university.edu")
                .password(passwordEncoder.encode("Pass123!"))
                .fullName("Admin Albus Dumbledore")
                .role(Role.ADMIN)
                .build();

        User studentUser = User.builder()
                .id(301L)
                .email("student@university.edu")
                .password(passwordEncoder.encode("Pass123!"))
                .fullName("Student Harry Potter")
                .role(Role.STUDENT)
                .build();

        when(userRepository.findByEmail("teacher@university.edu")).thenReturn(Optional.of(teacherUser));
        when(userRepository.findByEmail("admin@university.edu")).thenReturn(Optional.of(adminUser));
        when(userRepository.findByEmail("student@university.edu")).thenReturn(Optional.of(studentUser));

        teacherToken = jwtTokenProvider.generateToken(teacherUser.getEmail(), teacherUser.getRole().name(), teacherUser.getId(), teacherUser.getFullName());
        adminToken = jwtTokenProvider.generateToken(adminUser.getEmail(), adminUser.getRole().name(), adminUser.getId(), adminUser.getFullName());
        studentToken = jwtTokenProvider.generateToken(studentUser.getEmail(), studentUser.getRole().name(), studentUser.getId(), studentUser.getFullName());
    }

    // 1. TEACHER accessing teacher API -> allowed
    @Test
    @DisplayName("TEACHER role accessing /api/teacher/profile returns 200 OK")
    void teacherAccessingTeacherApi_Allowed() throws Exception {
        UserResponse userResponse = UserResponse.builder()
                .id(101L)
                .email("teacher@university.edu")
                .fullName("Prof. Minerva McGonagall")
                .role(Role.TEACHER)
                .build();
        when(userService.getUserByEmail("teacher@university.edu")).thenReturn(userResponse);

        mockMvc.perform(get("/api/teacher/profile")
                        .header("Authorization", "Bearer " + teacherToken)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email", is("teacher@university.edu")))
                .andExpect(jsonPath("$.role", is("TEACHER")));
    }

    // 2. ADMIN accessing teacher API -> allowed by configured rules
    @Test
    @DisplayName("ADMIN role accessing /api/teacher/profile returns 200 OK")
    void adminAccessingTeacherApi_Allowed() throws Exception {
        UserResponse userResponse = UserResponse.builder()
                .id(201L)
                .email("admin@university.edu")
                .fullName("Admin Albus Dumbledore")
                .role(Role.ADMIN)
                .build();
        when(userService.getUserByEmail("admin@university.edu")).thenReturn(userResponse);

        mockMvc.perform(get("/api/teacher/profile")
                        .header("Authorization", "Bearer " + adminToken)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email", is("admin@university.edu")))
                .andExpect(jsonPath("$.role", is("ADMIN")));
    }

    // 3. STUDENT accessing teacher API -> denied (403 Forbidden)
    @Test
    @DisplayName("STUDENT role accessing /api/teacher/profile returns 403 Forbidden")
    void studentAccessingTeacherApi_Forbidden() throws Exception {
        mockMvc.perform(get("/api/teacher/profile")
                        .header("Authorization", "Bearer " + studentToken)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.status", is(403)))
                .andExpect(jsonPath("$.error", is("Forbidden")));
    }

    // 4. Unauthenticated user accessing teacher API -> denied (401 Unauthorized)
    @Test
    @DisplayName("Unauthenticated user accessing /api/teacher/profile returns 401 Unauthorized")
    void unauthenticatedAccessingTeacherApi_Unauthorized() throws Exception {
        mockMvc.perform(get("/api/teacher/profile")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.status", is(401)))
                .andExpect(jsonPath("$.error", is("Unauthorized")));
    }

    // 5. ADMIN accessing admin API -> allowed (200 OK)
    @Test
    @DisplayName("ADMIN role accessing /api/admin/departments returns 200 OK")
    void adminAccessingAdminApi_Allowed() throws Exception {
        when(departmentService.getAllDepartments()).thenReturn(List.of(
                DepartmentResponse.builder().id(1L).name("Computer Science").code("CSE").build()
        ));

        mockMvc.perform(get("/api/admin/departments")
                        .header("Authorization", "Bearer " + adminToken)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].code", is("CSE")));
    }

    // 6. TEACHER accessing admin API -> denied (403 Forbidden)
    @Test
    @DisplayName("TEACHER role accessing /api/admin/departments returns 403 Forbidden")
    void teacherAccessingAdminApi_Forbidden() throws Exception {
        mockMvc.perform(get("/api/admin/departments")
                        .header("Authorization", "Bearer " + teacherToken)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.status", is(403)))
                .andExpect(jsonPath("$.error", is("Forbidden")));
    }

    // 7. STUDENT accessing admin API -> denied (403 Forbidden)
    @Test
    @DisplayName("STUDENT role accessing /api/admin/departments returns 403 Forbidden")
    void studentAccessingAdminApi_Forbidden() throws Exception {
        mockMvc.perform(get("/api/admin/departments")
                        .header("Authorization", "Bearer " + studentToken)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.status", is(403)))
                .andExpect(jsonPath("$.error", is("Forbidden")));
    }

    // 8. Unauthenticated user accessing admin API -> denied (401 Unauthorized)
    @Test
    @DisplayName("Unauthenticated user accessing /api/admin/departments returns 401 Unauthorized")
    void unauthenticatedAccessingAdminApi_Unauthorized() throws Exception {
        mockMvc.perform(get("/api/admin/departments")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.status", is(401)))
                .andExpect(jsonPath("$.error", is("Unauthorized")));
    }
}
