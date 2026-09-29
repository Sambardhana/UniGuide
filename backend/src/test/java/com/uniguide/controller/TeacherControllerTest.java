package com.uniguide.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.uniguide.dto.CourseResponse;
import com.uniguide.dto.DepartmentResponse;
import com.uniguide.dto.NoticeRequest;
import com.uniguide.dto.NoticeResponse;
import com.uniguide.dto.UserResponse;
import com.uniguide.entity.Role;
import com.uniguide.exception.GlobalExceptionHandler;
import com.uniguide.service.CourseService;
import com.uniguide.service.DepartmentService;
import com.uniguide.service.NoticeService;
import com.uniguide.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class TeacherControllerTest {

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @Mock
    private UserService userService;

    @Mock
    private DepartmentService departmentService;

    @Mock
    private CourseService courseService;

    @Mock
    private NoticeService noticeService;

    @InjectMocks
    private TeacherController teacherController;

    private Principal teacherPrincipal;
    private UserResponse teacherUser;
    private DepartmentResponse departmentResponse;
    private CourseResponse courseResponse;
    private NoticeResponse noticeResponse;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(teacherController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();

        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        teacherPrincipal = () -> "teacher@university.edu";

        teacherUser = UserResponse.builder()
                .id(101L)
                .email("teacher@university.edu")
                .fullName("Prof. Minerva McGonagall")
                .role(Role.TEACHER)
                .phoneNumber("+1-555-0199")
                .departmentId(1L)
                .departmentName("Computer Science & Engineering")
                .build();

        departmentResponse = DepartmentResponse.builder()
                .id(1L)
                .name("Computer Science & Engineering")
                .code("CSE")
                .description("Department of Computer Science")
                .build();

        courseResponse = CourseResponse.builder()
                .id(10L)
                .code("CS101")
                .title("Introduction to Computer Science")
                .credits(4)
                .semester(1)
                .departmentId(1L)
                .departmentName("Computer Science & Engineering")
                .build();

        noticeResponse = NoticeResponse.builder()
                .id(20L)
                .title("Midterm Exam Guidelines")
                .content("Midterm exams will commence next Monday.")
                .category("EXAMINATION")
                .departmentId(1L)
                .publishedAt(LocalDateTime.now())
                .build();
    }

    @Test
    @DisplayName("GET /api/teacher/profile returns authenticated teacher profile")
    void getTeacherProfile_Success() throws Exception {
        when(userService.getUserByEmail("teacher@university.edu")).thenReturn(teacherUser);

        mockMvc.perform(get("/api/teacher/profile")
                        .principal(teacherPrincipal)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(101)))
                .andExpect(jsonPath("$.email", is("teacher@university.edu")))
                .andExpect(jsonPath("$.fullName", is("Prof. Minerva McGonagall")))
                .andExpect(jsonPath("$.role", is("TEACHER")))
                .andExpect(jsonPath("$.departmentId", is(1)));

        verify(userService).getUserByEmail("teacher@university.edu");
    }

    @Test
    @DisplayName("GET /api/teacher/department returns associated department")
    void getTeacherDepartment_Success() throws Exception {
        when(userService.getUserByEmail("teacher@university.edu")).thenReturn(teacherUser);
        when(departmentService.getDepartmentById(1L)).thenReturn(departmentResponse);

        mockMvc.perform(get("/api/teacher/department")
                        .principal(teacherPrincipal)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.code", is("CSE")))
                .andExpect(jsonPath("$.name", is("Computer Science & Engineering")));

        verify(departmentService).getDepartmentById(1L);
    }

    @Test
    @DisplayName("GET /api/teacher/department returns 404 when teacher has no assigned department")
    void getTeacherDepartment_NotFound() throws Exception {
        UserResponse unassignedTeacher = UserResponse.builder()
                .id(102L)
                .email("teacher@university.edu")
                .role(Role.TEACHER)
                .departmentId(null)
                .build();

        when(userService.getUserByEmail("teacher@university.edu")).thenReturn(unassignedTeacher);

        mockMvc.perform(get("/api/teacher/department")
                        .principal(teacherPrincipal)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status", is(404)));
    }

    @Test
    @DisplayName("GET /api/teacher/courses returns department courses")
    void getTeacherCourses_Success() throws Exception {
        when(userService.getUserByEmail("teacher@university.edu")).thenReturn(teacherUser);
        when(courseService.getCoursesByDepartment(1L)).thenReturn(List.of(courseResponse));

        mockMvc.perform(get("/api/teacher/courses")
                        .principal(teacherPrincipal)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].code", is("CS101")));

        verify(courseService).getCoursesByDepartment(1L);
    }

    @Test
    @DisplayName("GET /api/teacher/courses with semester returns filtered courses")
    void getTeacherCourses_WithSemester_Success() throws Exception {
        when(userService.getUserByEmail("teacher@university.edu")).thenReturn(teacherUser);
        when(courseService.getCoursesByDepartmentAndSemester(1L, 1)).thenReturn(List.of(courseResponse));

        mockMvc.perform(get("/api/teacher/courses")
                        .principal(teacherPrincipal)
                        .param("semester", "1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].semester", is(1)));

        verify(courseService).getCoursesByDepartmentAndSemester(1L, 1);
    }

    @Test
    @DisplayName("GET /api/teacher/schedule returns class schedule courses")
    void getTeacherSchedule_Success() throws Exception {
        when(userService.getUserByEmail("teacher@university.edu")).thenReturn(teacherUser);
        when(courseService.getCoursesByDepartment(1L)).thenReturn(List.of(courseResponse));

        mockMvc.perform(get("/api/teacher/schedule")
                        .principal(teacherPrincipal)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].code", is("CS101")));
    }

    @Test
    @DisplayName("GET /api/teacher/notices returns department notices")
    void getTeacherNotices_Success() throws Exception {
        when(userService.getUserByEmail("teacher@university.edu")).thenReturn(teacherUser);
        when(noticeService.getNoticesByDepartment(1L)).thenReturn(List.of(noticeResponse));

        mockMvc.perform(get("/api/teacher/notices")
                        .principal(teacherPrincipal)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].title", is("Midterm Exam Guidelines")));

        verify(noticeService).getNoticesByDepartment(1L);
    }

    @Test
    @DisplayName("POST /api/teacher/notices creates notice and returns 201 Created")
    void createTeacherNotice_Success() throws Exception {
        NoticeRequest request = NoticeRequest.builder()
                .title("Midterm Exam Guidelines")
                .content("Midterm exams will commence next Monday.")
                .category("EXAMINATION")
                .build();

        when(userService.getUserByEmail("teacher@university.edu")).thenReturn(teacherUser);
        when(noticeService.createNotice(any(NoticeRequest.class))).thenReturn(noticeResponse);

        mockMvc.perform(post("/api/teacher/notices")
                        .principal(teacherPrincipal)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(20)))
                .andExpect(jsonPath("$.title", is("Midterm Exam Guidelines")));

        verify(noticeService).createNotice(any(NoticeRequest.class));
    }

    @Test
    @DisplayName("GET /api/teacher/students returns departmental students")
    void getDepartmentStudents_Success() throws Exception {
        UserResponse studentUser = UserResponse.builder()
                .id(301L)
                .email("student@university.edu")
                .fullName("Harry Potter")
                .role(Role.STUDENT)
                .studentId("STU-1001")
                .departmentId(1L)
                .build();

        UserResponse otherTeacher = UserResponse.builder()
                .id(302L)
                .email("colleague@university.edu")
                .fullName("Prof. Filius Flitwick")
                .role(Role.TEACHER)
                .departmentId(1L)
                .build();

        when(userService.getUserByEmail("teacher@university.edu")).thenReturn(teacherUser);
        when(userService.getUsersByDepartment(1L)).thenReturn(List.of(studentUser, otherTeacher));

        mockMvc.perform(get("/api/teacher/students")
                        .principal(teacherPrincipal)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].studentId", is("STU-1001")))
                .andExpect(jsonPath("$[0].fullName", is("Harry Potter")));

        verify(userService).getUsersByDepartment(1L);
    }
}
